package com.innobyteservices.quizzy.api.repositories.implementations;

import com.innobyteservices.quizzy.api.internals.StoredProcedureRequest;
import com.innobyteservices.quizzy.api.internals.StoredProcedureResult;
import com.innobyteservices.quizzy.api.repositories.interfaces.IBaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Implementation of the {@link IBaseRepository} interface for executing stored procedures using JPA's {@link EntityManager}.
 *
 * <p>This class provides a reusable mechanism to execute stored procedures with support for input (IN),
 * output (OUT), and input-output (INOUT) parameters. It captures multiple result sets and output parameter values.</p>
 */
@Repository
public class BaseRepository implements IBaseRepository {

    /**
     * The {@link EntityManager} instance used to manage persistence and execute stored procedures.
     */
    private final EntityManager _manager;

    /**
     * Constructs a new {@code BaseRepository} with the given {@link EntityManager}.
     *
     * @param manager the entity manager to be used for procedure execution
     */
    public BaseRepository(EntityManager manager) {
        this._manager = manager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StoredProcedureResult execute(StoredProcedureRequest request) {
        StoredProcedureQuery query = _manager.createStoredProcedureQuery(request.getName());

        if (request.getInParameters() != null) {
            for (Map.Entry<String, Object> param : request.getInParameters().entrySet()) {
                query.registerStoredProcedureParameter(param.getKey(), param.getValue().getClass(), ParameterMode.IN);
                query.setParameter(param.getKey(), param.getValue());
            }
        }

        if (request.getInOutParameters() != null) {
            for (Map.Entry<String, Object> param : request.getInOutParameters().entrySet()) {
                query.registerStoredProcedureParameter(param.getKey(), param.getValue().getClass(), ParameterMode.INOUT);
                query.setParameter(param.getKey(), param.getValue());
            }
        }

        boolean hasResultSet = query.execute();

        List<List<Object>> resultSets = new ArrayList<>();
        if (hasResultSet) {
            resultSets.add(query.getResultList());
            while (query.hasMoreResults()) {
                List<Object> nextResult = query.getResultList();
                if (nextResult != null) {
                    resultSets.add(nextResult);
                }
            }
        }

        Map<String, Object> outValues = new HashMap<>();

        if (request.getOutParameters() != null) {
            for (String paramName : request.getOutParameters()) {
                outValues.put(paramName, query.getOutputParameterValue(paramName));
            }
        }

        if (request.getInOutParameters() != null) {
            for (String paramName : request.getInOutParameters().keySet()) {
                outValues.put(paramName, query.getOutputParameterValue(paramName));
            }
        }

        StoredProcedureResult result = new StoredProcedureResult();
        result.setResultSets(resultSets);
        result.setOutParameters(outValues);
        return result;
    }
}

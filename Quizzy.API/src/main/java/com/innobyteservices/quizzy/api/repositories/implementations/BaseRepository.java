package com.innobyteservices.quizzy.api.repositories.implementations;

import com.innobyteservices.quizzy.api.internals.FunctionRequest;
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
    public <T> T execute(FunctionRequest<T> request) {
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(request.getName());
        queryBuilder.append("(");
        queryBuilder.append("?".repeat(Math.max(0, request.getParameters().size())));
        queryBuilder.append(") FROM dual");

        if (request.getParameters().size() > 0) {
            queryBuilder.setLength(queryBuilder.length() - "?) FROM dual".length());
            for (int i = 0; i < request.getParameters().size(); i++) {
                queryBuilder.append("?");
                if (i < request.getParameters().size() - 1) {
                    queryBuilder.append(", ");
                }
            }
            queryBuilder.append(") FROM dual");
        }

        var nativeQuery = _manager.createNativeQuery(queryBuilder.toString());

        for (int i = 0; i < request.getParameters().size(); i++) {
            nativeQuery.setParameter(i + 1, request.getParameters().get(i));
        }

        Object result = nativeQuery.getSingleResult();

        try {
            return request.getType().cast(result);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Cannot cast result to " + request.getType().getName(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StoredProcedureResult execute(StoredProcedureRequest request) {
        StoredProcedureQuery query = _manager.createStoredProcedureQuery(request.getName());

        for (Map.Entry<String, Object> param : request.getInParameters()) {
            String key = param.getKey();
            Object value = param.getValue();
            Class<?> paramType = request.getParameterTypes().get(key);

            if (paramType == null) {
                throw new IllegalArgumentException("Missing parameter type for IN parameter: " + key);
            }

            query.registerStoredProcedureParameter(key, paramType, ParameterMode.IN);
            query.setParameter(key, value);
        }

        for (Map.Entry<String, Object> param : request.getInOutParameters()) {
            String key = param.getKey();
            Object value = param.getValue();
            Class<?> paramType = request.getParameterTypes().get(key);

            if (paramType == null) {
                throw new IllegalArgumentException("Missing parameter type for INOUT parameter: " + key);
            }

            query.registerStoredProcedureParameter(key, paramType, ParameterMode.INOUT);
            query.setParameter(key, value);
        }

        for (String paramName : request.getOutParameters()) {
            Class<?> paramType = request.getParameterTypes().get(paramName);

            if (paramType == null) {
                throw new IllegalArgumentException("Missing parameter type for OUT parameter: " + paramName);
            }

            query.registerStoredProcedureParameter(paramName, paramType, ParameterMode.OUT);
        }

        boolean hasResultSet = query.execute();

        List<List<Object>> resultSets = new ArrayList<>();
        if (hasResultSet) {
            resultSets.add(query.getResultList());

            try {
                while (query.hasMoreResults()) {
                    List<Object> nextResult = query.getResultList();
                    if (nextResult != null) {
                        resultSets.add(nextResult);
                    }
                }
            } catch (Exception e) {
                // Some JPA providers do not support hasMoreResults; ignore or log if needed
            }
        }

        Map<String, Object> outValues = new HashMap<>();

        for (String paramName : request.getOutParameters()) {
            outValues.put(paramName, query.getOutputParameterValue(paramName));
        }

        for (Map.Entry<String, Object> param : request.getInOutParameters()) {
            String key = param.getKey();
            outValues.put(key, query.getOutputParameterValue(key));
        }

        StoredProcedureResult result = new StoredProcedureResult();
        result.setResultSets(resultSets);
        result.setOutParameters(outValues);
        return result;
    }
}

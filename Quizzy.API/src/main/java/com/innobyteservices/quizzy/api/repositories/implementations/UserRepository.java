package com.innobyteservices.quizzy.api.repositories.implementations;

import com.innobyteservices.quizzy.api.internals.UserFilter;
import com.innobyteservices.quizzy.api.internals.StoredProcedureRequest;
import com.innobyteservices.quizzy.api.internals.StoredProcedureResult;
import com.innobyteservices.quizzy.api.models.User;
import com.innobyteservices.quizzy.api.repositories.interfaces.IBaseRepository;
import com.innobyteservices.quizzy.api.repositories.interfaces.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Implementation of {@link IUserRepository} that interacts with the database using stored procedures.
 * <p>
 * This class uses {@link IBaseRepository} to execute stored procedures for user-related operations,
 * such as user creation and retrieval.
 * </p>
 */
@Repository
public class UserRepository implements IUserRepository {

    private final IBaseRepository _base;
    private final ModelMapper _mapper;

    /**
     * Constructs a new {@code UserRepository} with the specified base repository and model mapper.
     *
     * @param base   the {@link IBaseRepository} instance used for executing stored procedures
     * @param mapper the {@link ModelMapper} instance used for mapping between entities and DTOs
     */
    public UserRepository(IBaseRepository base, ModelMapper mapper) {
        _base = base;
        _mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Integer> create(User user) {
        StoredProcedureRequest request = new StoredProcedureRequest();
        request.setName("usp_register_user");

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_firstname", user.getFirstname());
        inParams.put("p_lastname", user.getLastname());
        inParams.put("p_email", user.getEmail());
        inParams.put("p_password", user.getPassword());
        inParams.put("p_role_id", user.getRole().getValue());

        ArrayList<String> outParams = new ArrayList<>();
        outParams.add("p_user_id");

        request.setInParameters(inParams);
        request.setOutParameters(outParams);
        StoredProcedureResult result = _base.execute(request);

        Map<String, Object> output = result.getOutParameters();
        if (output.containsKey("p_user_id")) {
            Object userId = output.get("p_user_id");
            if (userId instanceof Number) {
                return Optional.of(((Number) userId).intValue());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> get(UserFilter filter) {
        StoredProcedureRequest request = new StoredProcedureRequest();
        request.setName("usp_get_user");

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_id", filter.getId());
        inParams.put("p_email", filter.getEmail());

        StoredProcedureResult result = _base.execute(request);
        List<List<Object>> resultSets = result.getResultSets();

        if (resultSets != null &&
            !resultSets.isEmpty() &&
            resultSets.getFirst() != null &&
            !resultSets.getFirst().isEmpty()) {
            Object user = resultSets.getFirst().getFirst();
            return Optional.of(_mapper.map(user, User.class));
        }
        else {
            return Optional.empty();
        }
    }
}

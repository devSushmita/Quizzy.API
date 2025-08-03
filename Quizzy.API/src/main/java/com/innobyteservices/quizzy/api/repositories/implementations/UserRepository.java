package com.innobyteservices.quizzy.api.repositories.implementations;

import com.innobyteservices.quizzy.api.enums.AccessRole;
import com.innobyteservices.quizzy.api.internals.UserFilter;
import com.innobyteservices.quizzy.api.internals.StoredProcedureRequest;
import com.innobyteservices.quizzy.api.internals.StoredProcedureResult;
import com.innobyteservices.quizzy.api.entities.User;
import com.innobyteservices.quizzy.api.repositories.interfaces.IBaseRepository;
import com.innobyteservices.quizzy.api.repositories.interfaces.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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
    public Integer create(User user) {
        List<Map.Entry<String, Object>> inParams = new ArrayList<>();
        inParams.add(new AbstractMap.SimpleEntry<>("p_firstname", user.getFirstname()));
        inParams.add(new AbstractMap.SimpleEntry<>("p_lastname", user.getLastname()));
        inParams.add(new AbstractMap.SimpleEntry<>("p_role_id", user.getRole().getValue()));
        inParams.add(new AbstractMap.SimpleEntry<>("p_email", user.getEmail()));
        inParams.add(new AbstractMap.SimpleEntry<>("p_password", user.getPassword()));

        ArrayList<String> outParams = new ArrayList<>();
        outParams.add("p_user_id");

        Map<String, Class<?>> parameterTypes = new HashMap<>();
        parameterTypes.put("p_firstname", String.class);
        parameterTypes.put("p_lastname", String.class);
        parameterTypes.put("p_role_id", Integer.class);
        parameterTypes.put("p_email", String.class);
        parameterTypes.put("p_password", String.class);
        parameterTypes.put("p_user_id", Integer.class);

        StoredProcedureRequest request = new StoredProcedureRequest();
        request.setName("usp_register_user");
        request.setInParameters(inParams);
        request.setOutParameters(outParams);
        request.setParameterTypes(parameterTypes);
        StoredProcedureResult result = _base.execute(request);

        Map<String, Object> output = result.getOutParameters();
        if (output.containsKey("p_user_id")) {
            Object userId = output.get("p_user_id");
            if (userId instanceof Number) {
                return ((Number) userId).intValue();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User get(UserFilter filter) {
        List<Map.Entry<String, Object>> inParams = new ArrayList<>();
        inParams.add(new AbstractMap.SimpleEntry<>("p_id", null));
        inParams.add(new AbstractMap.SimpleEntry<>("p_email", filter.getEmail() != null ? filter.getEmail() : null));

        Map<String, Class<?>> parameterTypes = new HashMap<>();
        parameterTypes.put("p_id", Integer.class);
        parameterTypes.put("p_email", String.class);

        StoredProcedureRequest request = new StoredProcedureRequest();
        request.setName("usp_get_user");
        request.setInParameters(inParams);
        request.setParameterTypes(parameterTypes);
        StoredProcedureResult result = _base.execute(request);
        List<List<Object>> resultSets = result.getResultSets();

        if (resultSets != null &&
            !resultSets.isEmpty() &&
            resultSets.getFirst() != null &&
            !resultSets.getFirst().isEmpty()) {

            Object[] row = (Object[]) resultSets.getFirst().getFirst();

            User user = new User();
            user.setId((Integer) row[0]);
            user.setFirstname((String) row[1]);
            user.setLastname((String) row[2]);
            user.setRole(AccessRole.fromValue((Integer) row[3]));
            user.setEmail((String) row[4]);
            user.setPassword((String) row[5]);
            user.setCreatedAt((Timestamp) row[6]);

            return user;
        }
        else {
            return null;
        }
    }
}

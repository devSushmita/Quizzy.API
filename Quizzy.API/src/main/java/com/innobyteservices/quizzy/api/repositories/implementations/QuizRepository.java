package com.innobyteservices.quizzy.api.repositories.implementations;

import com.innobyteservices.quizzy.api.internals.StoredProcedureRequest;
import com.innobyteservices.quizzy.api.repositories.interfaces.IBaseRepository;
import com.innobyteservices.quizzy.api.repositories.interfaces.IQuizRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Repository implementation for quiz-related database operations.
 * <p>
 * This class is responsible for executing quiz-specific stored procedures,
 * such as deleting a quiz from the database.
 */
@Repository
public class QuizRepository implements IQuizRepository {

    private final IBaseRepository _base;

    /**
     * Constructs a new instance of {@code QuizRepository} with the given base repository.
     *
     * @param base the base repository used to execute stored procedures
     */
    QuizRepository(IBaseRepository base) {
        _base = base;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Executes the stored procedure {@code usp_delete_quiz} to delete a quiz
     * by its ID. Also sets a hardcoded deletedBy user ID (1).
     *
     * @param id the unique identifier of the quiz to delete
     */
    @Override
    public void delete(Integer id) {
        List<Map.Entry<String, Object>> inParams = new ArrayList<>();
        inParams.add(new AbstractMap.SimpleEntry<>("p_quiz_id", id));
        inParams.add(new AbstractMap.SimpleEntry<>("p_deleted_by", 1));

        Map<String, Class<?>> parameterTypes = new HashMap<>();
        parameterTypes.put("p_quiz_id", Integer.class);
        parameterTypes.put("p_deleted_by", Integer.class);

        StoredProcedureRequest request = new StoredProcedureRequest();
        request.setName("usp_delete_quiz");
        request.setInParameters(inParams);
        request.setParameterTypes(parameterTypes);

        _base.execute(request);
    }
}

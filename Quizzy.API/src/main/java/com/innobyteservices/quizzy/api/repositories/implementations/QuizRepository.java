package com.innobyteservices.quizzy.api.repositories.implementations;

import com.innobyteservices.quizzy.api.entities.Quiz;
import com.innobyteservices.quizzy.api.internals.StoredProcedureRequest;
import com.innobyteservices.quizzy.api.internals.StoredProcedureResult;
import com.innobyteservices.quizzy.api.repositories.interfaces.IBaseRepository;
import com.innobyteservices.quizzy.api.repositories.interfaces.IQuizRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.*;

/**
 * Repository implementation for quiz-related database operations.
 * <p>
 * Handles creation and deletion of quizzes through stored procedures.
 */
@Repository
public class QuizRepository implements IQuizRepository {

    private final IBaseRepository _base;

    /**
     * Constructs a new instance of {@code QuizRepository} with the given base repository.
     *
     * @param base the base repository used to execute stored procedures
     */
    public QuizRepository(IBaseRepository base) {
        this._base = base;
    }

    /**
     * {@inheritDoc}
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer create(Quiz quiz) {
        List<Map.Entry<String, Object>> inParams = new ArrayList<>();
        inParams.add(new AbstractMap.SimpleEntry<>("p_name", quiz.getName()));
        inParams.add(new AbstractMap.SimpleEntry<>("p_topic_id", quiz.getTopicId()));
        inParams.add(new AbstractMap.SimpleEntry<>("p_duration", quiz.getDuration()));
        inParams.add(new AbstractMap.SimpleEntry<>("p_createdBy", quiz.getCreatedBy()));
        inParams.add(new AbstractMap.SimpleEntry<>("p_total_questions", quiz.getTotalQuestions()));

        ArrayList<String> outParams = new ArrayList<>();
        outParams.add("p_quiz_id");

        Map<String, Class<?>> parameterTypes = new HashMap<>();
        parameterTypes.put("p_name", String.class);
        parameterTypes.put("p_topic_id", Integer.class);
        parameterTypes.put("p_duration", Integer.class);
        parameterTypes.put("p_createdBy", Integer.class);
        parameterTypes.put("p_total_questions", Integer.class);
        parameterTypes.put("p_quiz_id", Integer.class);

        StoredProcedureRequest request = new StoredProcedureRequest();
        request.setName("usp_create_quiz");
        request.setInParameters(inParams);
        request.setOutParameters(outParams);
        request.setParameterTypes(parameterTypes);

        StoredProcedureResult result = _base.execute(request);
        Map<String, Object> output = result.getOutParameters();

        if (output.containsKey("p_quiz_id")) {
            Object quizId = output.get("p_quiz_id");
            if (quizId instanceof Number) {
                return ((Number) quizId).intValue();
            }
        }

        return null;
    }
}

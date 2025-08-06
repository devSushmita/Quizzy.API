package com.innobyteservices.quizzy.api.repositories.implementations;

import com.innobyteservices.quizzy.api.entities.Question;
import com.innobyteservices.quizzy.api.internals.StoredProcedureRequest;
import com.innobyteservices.quizzy.api.internals.StoredProcedureResult;
import com.innobyteservices.quizzy.api.repositories.interfaces.IBaseRepository;
import com.innobyteservices.quizzy.api.repositories.interfaces.IQuestionRepository;

import java.util.*;

public class QuestionRepository implements IQuestionRepository {
    private final IBaseRepository _base;

    public QuestionRepository(IBaseRepository base) {
        _base = base;
    }

    @Override
    public Integer create(Question question) {
        List<Map.Entry<String, Object>> inParams = new ArrayList<>();
        inParams.add(new AbstractMap.SimpleEntry<>("p_id", question.getId()));
        inParams.add(new AbstractMap.SimpleEntry<>("p_title", question.getTitle()));
        inParams.add(new AbstractMap.SimpleEntry<>("p_level", question.getLevel()));
        inParams.add(new AbstractMap.SimpleEntry<>("p_marks", question.getMarks()));
        inParams.add(new AbstractMap.SimpleEntry<>("p_answer_id", question.getAnswerId()));
        inParams.add(new AbstractMap.SimpleEntry<>("p_topic_id", question.getTopicId()));
        inParams.add(new AbstractMap.SimpleEntry<>("p_created_at", question.getCreatedAt()));
        inParams.add(new AbstractMap.SimpleEntry<>("p_updated_at", question.getUpdatedAt()));
        inParams.add(new AbstractMap.SimpleEntry<>("p_created_by", question.getCreatedBy()));
        inParams.add(new AbstractMap.SimpleEntry<>("p_updated_by", question.getUpdatedBy()));

        List<String> outParams = List.of("p_question_id");

        Map<String, Class<?>> parameterTypes = new HashMap<>();
        parameterTypes.put("p_id", Integer.class);
        parameterTypes.put("p_title", String.class);
        parameterTypes.put("p_level", Integer.class);
        parameterTypes.put("p_marks", Integer.class);
        parameterTypes.put("p_answer_id", Integer.class);
        parameterTypes.put("p_topic_id", Integer.class);
        parameterTypes.put("p_created_at", java.sql.Timestamp.class);
        parameterTypes.put("p_updated_at", java.sql.Timestamp.class);
        parameterTypes.put("p_created_by", Integer.class);
        parameterTypes.put("p_updated_by", Integer.class);
        parameterTypes.put("p_question_id", Integer.class); // OUT param

        StoredProcedureRequest request = new StoredProcedureRequest();
        request.setName("usp_create_question"); // fixed typo from `usp_crete_question`
        request.setInParameters(inParams);
        request.setOutParameters(outParams);
        request.setParameterTypes(parameterTypes);

        StoredProcedureResult result = _base.execute(request);

        Map<String, Object> output = result.getOutParameters();
        if (output.containsKey("p_question_id")) {
            Object questionId = output.get("p_question_id");
            if (questionId instanceof Number) {
                return ((Number) questionId).intValue();
            }
        }

        return null;
    }
}

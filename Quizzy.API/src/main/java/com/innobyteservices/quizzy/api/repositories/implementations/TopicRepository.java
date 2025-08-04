package com.innobyteservices.quizzy.api.repositories.implementations;

import com.innobyteservices.quizzy.api.entities.Topic;
import com.innobyteservices.quizzy.api.internals.StoredProcedureRequest;
import com.innobyteservices.quizzy.api.internals.StoredProcedureResult;
import com.innobyteservices.quizzy.api.repositories.interfaces.IBaseRepository;
import com.innobyteservices.quizzy.api.repositories.interfaces.ITopicRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Implementation of {@link ITopicRepository} that handles topic-related data operations.
 * <p>
 * This class interacts with the database using stored procedures and utilizes
 * a base repository to execute the requests. It also uses {@link ModelMapper} for potential mappings.
 */
@Repository
public class TopicRepository implements ITopicRepository {

    private final IBaseRepository _base;
    private final ModelMapper _mapper;

    /**
     * Constructs a {@code TopicRepository} with required dependencies.
     *
     * @param base   the base repository used to execute stored procedures
     * @param mapper the model mapper for object transformation (if needed)
     */
    public TopicRepository(IBaseRepository base, ModelMapper mapper) {
        _base = base;
        _mapper = mapper;
    }

    /**
     * Adds a new topic by executing the {@code usp_create_topic} stored procedure.
     * <p>
     * Accepts topic name and creator ID as input parameters, and returns the generated topic ID.
     *
     * @param topic the topic entity containing creation details
     * @return the generated topic ID, or {@code null} if creation fails or output is invalid
     */
    @Override
    public Integer add(Topic topic) {
        List<Map.Entry<String, Object>> inParams = new ArrayList<>();
        inParams.add(new AbstractMap.SimpleEntry<>("p_name", topic.getName()));
        inParams.add(new AbstractMap.SimpleEntry<>("p_created_by", topic.getCreatedBy()));

        ArrayList<String> outParams = new ArrayList<>();
        outParams.add("p_topic_id");

        Map<String, Class<?>> parameterTypes = new HashMap<>();
        parameterTypes.put("p_name", String.class);
        parameterTypes.put("p_created_by", Integer.class);
        parameterTypes.put("p_topic_id", Integer.class);

        StoredProcedureRequest request = new StoredProcedureRequest();
        request.setName("usp_create_topic");
        request.setInParameters(inParams);
        request.setOutParameters(outParams);
        request.setParameterTypes(parameterTypes);

        StoredProcedureResult result = _base.execute(request);

        Map<String, Object> output = result.getOutParameters();
        if (output.containsKey("p_topic_id")) {
            Object topicId = output.get("p_topic_id");
            if (topicId instanceof Number) {
                return ((Number) topicId).intValue();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}

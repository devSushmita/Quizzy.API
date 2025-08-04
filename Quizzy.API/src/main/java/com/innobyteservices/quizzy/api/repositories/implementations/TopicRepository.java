package com.innobyteservices.quizzy.api.repositories.implementations;

import com.innobyteservices.quizzy.api.entities.Topic;
import com.innobyteservices.quizzy.api.internals.StoredProcedureRequest;
import com.innobyteservices.quizzy.api.internals.StoredProcedureResult;
import com.innobyteservices.quizzy.api.repositories.interfaces.IBaseRepository;
import com.innobyteservices.quizzy.api.repositories.interfaces.ITopicRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.*;

/**
 * Implementation of {@link ITopicRepository} that handles topic-related data operations.
 * <p>
 * This class interacts with the database using stored procedures and utilizes
 * a base repository to execute the requests. It also uses {@link ModelMapper} for potential mappings.

/**
 * Repository implementation for managing quiz topic data.
 * <p>
 * Executes stored procedures to retrieve topic information from the database
 * and maps the result into {@link Topic} entities.
 * </p>
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
     * {@inheritDoc}
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

    /**
     * {@inheritDoc}
     */
    public List<Topic> get() {
        StoredProcedureRequest request = new StoredProcedureRequest();
        request.setName("usp_get_topics");

        StoredProcedureResult result = _base.execute(request);
        List<List<Object>> resultSets = result.getResultSets();
        List<Topic> topics = new ArrayList<>();

        if (resultSets != null && !resultSets.isEmpty()) {
            Object firstResultSet = resultSets.getFirst();

            if (firstResultSet instanceof List<?> firstList && !firstList.isEmpty()) {
                @SuppressWarnings("unchecked")
                List<Object[]> rows = (List<Object[]>) firstResultSet;

                for (Object[] record : rows) {
                    Topic topic = new Topic();
                    topic.setId((Integer) record[0]);
                    topic.setName((String) record[1]);
                    topic.setCreatedAt((Timestamp) record[2]);
                    topic.setUpdatedAt((Timestamp) record[3]);
                    topic.setCreatedBy((Integer) record[4]);
                    topic.setUpdatedBy((Integer) record[5]);
                    topics.add(topic);
                }
            }
        }

        return topics;
    }
}

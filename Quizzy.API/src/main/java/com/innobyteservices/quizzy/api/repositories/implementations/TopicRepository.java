package com.innobyteservices.quizzy.api.repositories.implementations;

import com.innobyteservices.quizzy.api.entities.Topic;
import com.innobyteservices.quizzy.api.internals.StoredProcedureRequest;
import com.innobyteservices.quizzy.api.internals.StoredProcedureResult;
import com.innobyteservices.quizzy.api.repositories.interfaces.ITopicRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository implementation for managing quiz topic data.
 * <p>
 * Executes stored procedures to retrieve topic information from the database
 * and maps the result into {@link Topic} entities.
 * </p>
 */
@Repository
public class TopicRepository implements ITopicRepository {

    private final BaseRepository _base;

    /**
     * Constructs a {@code TopicRepository} with the provided {@code BaseRepository}.
     *
     * @param base the base repository used for executing stored procedures
     */
    public TopicRepository(BaseRepository base) {
        this._base = base;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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

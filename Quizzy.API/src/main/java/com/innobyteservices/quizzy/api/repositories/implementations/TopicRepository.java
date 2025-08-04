package com.innobyteservices.quizzy.api.repositories.implementations;

import com.innobyteservices.quizzy.api.entities.Topic;
import com.innobyteservices.quizzy.api.internals.StoredProcedureRequest;
import com.innobyteservices.quizzy.api.internals.StoredProcedureResult;
import com.innobyteservices.quizzy.api.repositories.interfaces.ITopicRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Repository
public class TopicRepository implements ITopicRepository {

    private final BaseRepository _base;

    public TopicRepository(BaseRepository base) {
        this._base = base;
    }

    @Override
    public List<Topic> get() {
        StoredProcedureRequest request = new StoredProcedureRequest();
        request.setName("usp_get_topics");

        StoredProcedureResult result = _base.execute(request);
        List<List<Object>> resultSets = result.getResultSets();

        List<Topic> topics = new ArrayList<>();

        if (resultSets != null &&
                !resultSets.isEmpty()) {

            Object firstResultSet = resultSets.getFirst();

            if (firstResultSet instanceof List<?> firstList && !firstList.isEmpty()) {
                @SuppressWarnings("unchecked")
                List<List<Object>> rows = (List<List<Object>>) firstResultSet;

                for (int i = 0; i < rows.size(); i++) {
                    var record = rows.get(i);
                    Topic topic = new Topic();
                    topic.setId((Integer) record.get(0));
                    topic.setName((String) record.get(1));
                    topic.setCreatedAt((Timestamp) record.get(2));
                    //topic.setUpdatedAt((Timestamp) row.get(3));
                    topic.setCreatedBy((Integer) record.get(4));
                    //topic.setUpdatedBy((Integer) row.get(5));
                    topics.add(topic);
                }
            }
        }

        return topics;
    }


}

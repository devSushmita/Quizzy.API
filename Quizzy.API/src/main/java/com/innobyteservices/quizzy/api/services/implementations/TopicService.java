package com.innobyteservices.quizzy.api.services.implementations;

import com.innobyteservices.quizzy.api.dto.response.TopicResponse;
import com.innobyteservices.quizzy.api.entities.Topic;
import com.innobyteservices.quizzy.api.repositories.interfaces.ITopicRepository;
import com.innobyteservices.quizzy.api.services.interfaces.ITopicService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link ITopicService} that handles business logic
 * related to quiz topics.
 *
 * <p>Fetches topic entities from the repository and maps them to response DTOs.</p>
 */
@Service
public class TopicService implements ITopicService {

    private final ITopicRepository _repository;

    /**
     * Creates a new {@code TopicService} with the specified topic repository.
     *
     * @param repository the repository used to access topic data
     */
    public TopicService(ITopicRepository repository) {
        _repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TopicResponse> get() {
        List<Topic> results = _repository.get();
        List<TopicResponse> responses = new ArrayList<>();

        for (Topic topic : results) {
            TopicResponse response = new TopicResponse();
            response.setId(topic.getId());
            response.setName(topic.getName());
            responses.add(response);
        }

        return responses;
    }
}

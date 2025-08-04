package com.innobyteservices.quizzy.api.services.implementations;

import com.innobyteservices.quizzy.api.dto.request.TopicCreationRequest;
import com.innobyteservices.quizzy.api.dto.response.TopicCreationResponse;
import com.innobyteservices.quizzy.api.entities.Topic;
import com.innobyteservices.quizzy.api.exceptions.InvalidRequestException;
import com.innobyteservices.quizzy.api.exceptions.TopicAlreadyExistsException;
import com.innobyteservices.quizzy.api.internals.CurrentUserContext;
import com.innobyteservices.quizzy.api.repositories.interfaces.ITopicRepository;
import com.innobyteservices.quizzy.api.services.interfaces.ITopicService;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for managing quiz topics.
 */
@Service
public class TopicService implements ITopicService {

    private final ITopicRepository _repository;
    private final CurrentUserContext _context;

    /**
     * Constructs a TopicService with the given repository and current user context.
     *
     * @param repository the topic repository used for persistence operations
     * @param context    the current user context containing JWT claim data
     */
    public TopicService(ITopicRepository repository, CurrentUserContext context) {
        _repository = repository;
        _context = context;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TopicCreationResponse add(TopicCreationRequest request) {
        if (request == null) {
            throw new InvalidRequestException();
        }

        Topic newTopic = new Topic();
        newTopic.setName(request.getName());
        newTopic.setCreatedBy(_context.getUserId());
        Integer topicId = _repository.add(newTopic);

        if (topicId != null) {
            TopicCreationResponse response = new TopicCreationResponse();
            response.setId(topicId);
            return response;
        } else {
            throw new TopicAlreadyExistsException();
        }
    }
}

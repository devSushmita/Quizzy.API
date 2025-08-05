package com.innobyteservices.quizzy.api.services.implementations;

import com.innobyteservices.quizzy.api.dto.request.TopicCreationRequest;
import com.innobyteservices.quizzy.api.dto.response.TopicCreationResponse;
import com.innobyteservices.quizzy.api.dto.response.TopicResponse;
import com.innobyteservices.quizzy.api.entities.Topic;
import com.innobyteservices.quizzy.api.exceptions.InvalidRequestException;
import com.innobyteservices.quizzy.api.exceptions.TopicAlreadyExistsException;
import com.innobyteservices.quizzy.api.exceptions.TopicCreationFailedException;
import com.innobyteservices.quizzy.api.internals.CurrentUserContext;
import com.innobyteservices.quizzy.api.repositories.interfaces.ITopicRepository;
import com.innobyteservices.quizzy.api.services.interfaces.ITopicService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link ITopicService} that handles business logic
 * related to quiz topics.
 * <p>
 * This service interacts with the topic repository to create and fetch topics,
 * and uses the current user context to associate topic creation with the authenticated user.
 * </p>
 */
@Service
public class TopicService implements ITopicService {

    private final ITopicRepository _repository;
    private final CurrentUserContext _context;

    /**
     * Constructs a {@code TopicService} with required dependencies.
     *
     * @param repository the topic repository used for persistence operations
     * @param context    the current user context containing JWT claim data
     */
    public TopicService(ITopicRepository repository, CurrentUserContext context) {
        _repository = repository;
        _context = context;
    }

    /**
     * Adds a new topic based on the provided request data.
     *
     * @param request the topic creation request containing the topic name
     * @return the response containing the generated topic ID
     */
    @Override
    public TopicCreationResponse add(TopicCreationRequest request) {
        if (request == null) {
            throw new InvalidRequestException();
        }

        Boolean doesExist = _repository.exists(request.getName());
        if (doesExist) {
            throw new TopicAlreadyExistsException();
        }

        Topic newTopic = new Topic();
        newTopic.setName(request.getName());
        newTopic.setCreatedBy(_context.getUserId());
        Integer topicId = _repository.add(newTopic);

        if (topicId != null) {
            TopicCreationResponse response = new TopicCreationResponse();
            response.setId(topicId);
            response.setName(request.getName());
            return response;
        } else {
            throw new TopicCreationFailedException();
        }
    }

    /**
     * Retrieves all topics from the repository and maps them to response DTOs.
     *
     * @return a list of {@link TopicResponse} containing topic details
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

package com.innobyteservices.quizzy.api.services.implementations;

import com.innobyteservices.quizzy.api.dto.request.TopicCreationRequest;
import com.innobyteservices.quizzy.api.dto.response.TopicCreationResponse;
import com.innobyteservices.quizzy.api.entities.Topic;
import com.innobyteservices.quizzy.api.exceptions.InvalidRequestException;
import com.innobyteservices.quizzy.api.exceptions.TopicAlreadyExistsException;
import com.innobyteservices.quizzy.api.repositories.interfaces.ITopicRepository;
import com.innobyteservices.quizzy.api.services.interfaces.ITopicService;
import org.springframework.stereotype.Service;

@Service
public class TopicService implements ITopicService {

    private final ITopicRepository _repository;

    public TopicService(ITopicRepository repository) {
        _repository = repository;
    }

    @Override
    public TopicCreationResponse add(TopicCreationRequest request) {
        if (request == null) {
            throw new InvalidRequestException();
        }

        Topic newTopic = new Topic();
        newTopic.setName(request.getName());
        newTopic.setName(request.getName());
        Integer topicId = _repository.add(newTopic);

        if (topicId != null) {
            TopicCreationResponse response = new TopicCreationResponse();
            response.setId(topicId);
            return response;
        }
        else {
            throw new TopicAlreadyExistsException();
        }
    }
}

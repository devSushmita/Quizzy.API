package com.innobyteservices.quizzy.api.services.implementations;

import com.innobyteservices.quizzy.api.dto.response.TopicResponse;
import com.innobyteservices.quizzy.api.entities.Topic;
import com.innobyteservices.quizzy.api.repositories.interfaces.ITopicRepository;
import com.innobyteservices.quizzy.api.services.interfaces.ITopicService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class TopicService implements ITopicService {

    private final ITopicRepository _repository;

    public TopicService(ITopicRepository repository) {
        _repository = repository;
    }

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

package com.innobyteservices.quizzy.api.services.interfaces;

import com.innobyteservices.quizzy.api.dto.response.TopicResponse;

import java.util.List;

public interface ITopicService {
    List<TopicResponse> get();
}

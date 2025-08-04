package com.innobyteservices.quizzy.api.controllers;

import com.innobyteservices.quizzy.api.dto.response.APIResponse;
import com.innobyteservices.quizzy.api.dto.response.TopicResponse;
import com.innobyteservices.quizzy.api.services.interfaces.ITopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing quiz topics.
 */
@RestController
@RequestMapping("/api/topics")
public class TopicsController {

    private final ITopicService _service;

    public TopicsController(ITopicService service) {
        _service = service;
    }

    /**
     * Retrieves all quiz topics.
     *
     * @return a list of {@link TopicResponse} wrapped in {@link APIResponse}
     */
    @GetMapping
    public ResponseEntity<APIResponse<List<TopicResponse>>> getTopics() {
        List<TopicResponse> topics = _service.get();
        APIResponse<List<TopicResponse>> response = new APIResponse<>();
        response.setData(topics);
        return ResponseEntity.ok(response);
    }
}

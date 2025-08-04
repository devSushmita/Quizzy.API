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
 * Controller that handles HTTP requests related to quiz topics.
 *
 * <p>
 * Provides an endpoint to fetch all available quiz topics.
 * </p>
 */
@RestController
@RequestMapping("/api/topics")
public class TopicsController {

    private final ITopicService _service;

    /**
     * Constructs a new {@code TopicsController} with the given service dependency.
     *
     * @param service the topic service used to interact with topic data
     */
    public TopicsController(ITopicService service) {
        _service = service;
    }

    /**
     * Handles GET requests to retrieve all quiz topics.
     *
     * @return a {@link ResponseEntity} containing an {@link APIResponse}
     *         with a list of {@link TopicResponse} objects
     */
    @GetMapping
    public ResponseEntity<APIResponse<List<TopicResponse>>> get() {
        List<TopicResponse> topics = _service.get();
        APIResponse<List<TopicResponse>> response = new APIResponse<>();
        response.setData(topics);
        return ResponseEntity.ok(response);
    }
}

package com.innobyteservices.quizzy.api.controllers;

import com.innobyteservices.quizzy.api.annotations.RequirePermission;
import com.innobyteservices.quizzy.api.dto.request.TopicCreationRequest;
import com.innobyteservices.quizzy.api.dto.response.APIResponse;
import com.innobyteservices.quizzy.api.dto.response.TopicCreationResponse;
import com.innobyteservices.quizzy.api.enums.AccessRole;
import com.innobyteservices.quizzy.api.services.interfaces.ITopicService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.innobyteservices.quizzy.api.dto.response.TopicResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Handles API requests related to quiz topics.
 * <p>
 * Provides endpoints to create and retrieve topics.
 * </p>
 */
@RestController
@RequestMapping("/api/topics")
public class TopicsController {

    private final ITopicService _service;

    /**
     * Creates a new instance of {@code TopicsController}.
     *
     * @param service the topic service to handle business logic
     */
    public TopicsController(ITopicService service) {
        _service = service;
    }

    /**
     * Adds a new topic.
     * Requires the user to have {@code Admin} access.
     *
     * @param request the topic creation request containing the topic name
     * @return API response containing the created topic's ID
     */
    @RequirePermission(role = AccessRole.Admin)
    @PostMapping
    public ResponseEntity<APIResponse<TopicCreationResponse>> add(@Valid @RequestBody TopicCreationRequest request) {
        TopicCreationResponse response = _service.add(request);
        APIResponse<TopicCreationResponse> apiResponse = new APIResponse<>();
        apiResponse.setData(response);
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Retrieves all available topics.
     *
     * @return API response containing the list of topics
     */
    @RequirePermission(role = AccessRole.User)
    @GetMapping
    public ResponseEntity<APIResponse<List<TopicResponse>>> get() {
        List<TopicResponse> topics = _service.get();
        APIResponse<List<TopicResponse>> response = new APIResponse<>();
        response.setData(topics);
        return ResponseEntity.ok(response);
    }
}

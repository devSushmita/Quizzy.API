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

/**
 * REST controller for managing quiz topics.
 * <p>
 * Provides an endpoint to add new topics.
 */
@RestController
@RequestMapping("/api/topics")
public class TopicsController {

    private final ITopicService _service;

    /**
     * Constructs a new {@code TopicsController} with the given topic service.
     *
     * @param service the topic service used for topic operations
     */
    TopicsController(ITopicService service) {
        _service = service;
    }

    /**
     * Adds a new topic.
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
}

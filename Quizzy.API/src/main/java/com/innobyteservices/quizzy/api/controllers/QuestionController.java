package com.innobyteservices.quizzy.api.controllers;

import com.innobyteservices.quizzy.api.annotations.RequirePermission;
import com.innobyteservices.quizzy.api.dto.request.QuestionCreationRequest;
import com.innobyteservices.quizzy.api.dto.response.APIResponse;
import com.innobyteservices.quizzy.api.dto.response.QuestionCreationResponse;
import com.innobyteservices.quizzy.api.enums.AccessRole;
import com.innobyteservices.quizzy.api.services.interfaces.IQuestionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling question-related endpoints.
 * <p>
 * Provides endpoints for creating quiz questions. Access is restricted
 * to users with admin permissions.
 */
@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final IQuestionService _service;

    /**
     * Constructs a new {@code QuestionController} with the specified service.
     *
     * @param service the service used for question operations
     */
    public QuestionController(IQuestionService service) {
        _service = service;
    }

    /**
     * Creates a new quiz question.
     *
     * @param request the question creation request
     * @return a response entity containing the created question's info
     */
    @RequirePermission(role = AccessRole.Admin)
    @PostMapping
    public ResponseEntity<APIResponse<QuestionCreationResponse>> create(
            @Valid @RequestBody QuestionCreationRequest request) {
        QuestionCreationResponse data = _service.create(request);

        APIResponse<QuestionCreationResponse> response = new APIResponse<>();
        response.setData(data);

        return ResponseEntity.ok(response);
    }
}

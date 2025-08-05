package com.innobyteservices.quizzy.api.controllers;

import com.innobyteservices.quizzy.api.annotations.RequirePermission;
import com.innobyteservices.quizzy.api.dto.request.QuizCreationRequest;
import com.innobyteservices.quizzy.api.dto.response.APIResponse;
import com.innobyteservices.quizzy.api.dto.response.QuizCreationResponse;
import com.innobyteservices.quizzy.api.entities.Quiz;
import com.innobyteservices.quizzy.api.enums.AccessRole;
import com.innobyteservices.quizzy.api.services.interfaces.IQuizService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling quiz-related API endpoints.
 * <p>
 * Provides endpoints to create and delete quizzes.
 * </p>
 */
@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final IQuizService _service;

    /**
     * Constructs a new {@code QuizController} with the given service.
     *
     * @param service the quiz service used to perform business operations
     */
    public QuizController(IQuizService service) {
        _service = service;
    }

    /**
     * Deletes a quiz by its ID.
     * <p>
     * Requires admin-level permission.
     * </p>
     *
     * @param id the ID of the quiz to delete
     * @return a response entity indicating success
     */
    @RequirePermission(role = AccessRole.Admin)
    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        _service.delete(id);
        APIResponse<Object> response = new APIResponse<>();
        return ResponseEntity.ok(response);
    }

    /**
     * Creates a new quiz.
     * <p>
     * Requires admin-level permission.
     * </p>
     *
     * @param quiz the quiz entity to be created
     * @return a response entity containing the created quiz's details
     */
    @RequirePermission(role = AccessRole.Admin)
    @PostMapping()
    public ResponseEntity<APIResponse<QuizCreationResponse>> create(@Valid @RequestBody QuizCreationRequest quiz) {
        QuizCreationResponse data = _service.create(quiz);
        APIResponse<QuizCreationResponse> response = new APIResponse<>();
        response.setData(data);
        return ResponseEntity.ok(response);
    }
}

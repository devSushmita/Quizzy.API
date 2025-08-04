package com.innobyteservices.quizzy.api.controllers;

import com.innobyteservices.quizzy.api.dto.response.APIResponse;
import com.innobyteservices.quizzy.api.services.interfaces.IQuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling quiz-related API endpoints.
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
     *
     * @param id the ID of the quiz to delete
     * @return a response entity indicating the result of the deletion
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        _service.delete(id);

        APIResponse<Object> response = new APIResponse<>();
        return ResponseEntity.ok(response);
    }

}

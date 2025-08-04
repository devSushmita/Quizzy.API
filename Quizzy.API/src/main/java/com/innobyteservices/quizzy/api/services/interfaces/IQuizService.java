package com.innobyteservices.quizzy.api.services.interfaces;

import com.innobyteservices.quizzy.api.dto.response.QuizCreationResponse;
import com.innobyteservices.quizzy.api.entities.Quiz;

/**
 * Service interface for quiz-related operations.
 */
public interface IQuizService {

    /**
     * Deletes a quiz by its unique ID.
     *
     * @param id the ID of the quiz to delete
     */
    void delete(Integer id);

    /**
     * Creates a new quiz after performing validation and setting metadata.
     *
     * @param quiz the {@link Quiz} entity to be created
     * @return a {@link QuizCreationResponse} containing the ID and name of the created quiz
     * @throws IllegalArgumentException if any required quiz fields are missing or invalid
     * @throws RuntimeException if quiz creation fails at the data access layer
     */
    QuizCreationResponse create(Quiz quiz);
}

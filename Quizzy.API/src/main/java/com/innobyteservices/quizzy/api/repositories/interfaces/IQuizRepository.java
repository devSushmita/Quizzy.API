package com.innobyteservices.quizzy.api.repositories.interfaces;

import com.innobyteservices.quizzy.api.entities.Quiz;

/**
 * Interface for quiz-related data access operations.
 * <p>
 * Provides methods to create and delete quizzes.
 */
public interface IQuizRepository {

    /**
     * Deletes a quiz by its ID.
     *
     * @param id the unique identifier of the quiz to be deleted
     */
    void delete(Integer id);

    /**
     * Creates a new quiz and returns the generated quiz ID.
     *
     * @param quiz the {@link Quiz} entity to be created
     * @return the generated quiz ID, or {@code null} if creation failed
     */
    Integer create(Quiz quiz);
}

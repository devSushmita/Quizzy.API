package com.innobyteservices.quizzy.api.repositories.interfaces;

import com.innobyteservices.quizzy.api.entities.Question;

/**
 * Repository interface for managing question-related database operations.
 */
public interface IQuestionRepository {

    /**
     * Creates a new question entry in the database.
     *
     * @param question the {@link Question} entity containing the details to be persisted
     * @return the generated ID of the created question, or {@code null} if creation failed
     */
    Integer create(Question question);
}

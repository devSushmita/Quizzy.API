package com.innobyteservices.quizzy.api.repositories.interfaces;

import com.innobyteservices.quizzy.api.entities.Quiz;

/**
 * Defines repository-level operations for managing quiz data.
 * <p>
 * Supports creation and deletion of quiz entities in the data store.
 */
public interface IQuizRepository {

    /**
     * Removes the quiz associated with the specified ID.
     *
     * @param id the unique ID of the quiz to be removed
     */
    void delete(Integer id);

    /**
     * Persists a new quiz entity and returns its generated ID.
     *
     * @param quiz the {@link Quiz} entity to persist
     * @return the generated quiz ID, or {@code null} if the operation failed
     */
    Integer create(Quiz quiz);
}

package com.innobyteservices.quizzy.api.repositories.interfaces;

/**
 * Interface for quiz-related data access operations.
 */
public interface IQuizRepository {

    /**
     * Deletes a quiz by its ID.
     *
     * @param id the unique identifier of the quiz to be deleted
     */
    void delete(Integer id);
}

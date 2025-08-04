package com.innobyteservices.quizzy.api.services.interfaces;

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
}

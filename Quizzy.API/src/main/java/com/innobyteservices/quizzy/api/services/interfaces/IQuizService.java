package com.innobyteservices.quizzy.api.services.interfaces;

import com.innobyteservices.quizzy.api.dto.request.QuizCreationRequest;
import com.innobyteservices.quizzy.api.dto.response.QuizCreationResponse;

/**
 * Defines business service operations related to quiz management.
 *
 * <p>This interface provides methods for creating and deleting quizzes,
 * along with enforcing validation and domain-specific rules.</p>
 */
public interface IQuizService {

    /**
     * Deletes a quiz based on its unique identifier.
     *
     * @param id the ID of the quiz to be deleted
     */
    void delete(Integer id);

    /**
     * Creates a new quiz from the provided request data.
     *
     * <p>Performs necessary validation before persisting the quiz to the database.</p>
     *
     * @param quiz the {@link QuizCreationRequest} containing quiz details such as name, topic, duration, and question count
     * @return a {@link QuizCreationResponse} containing the newly created quiz's ID and metadata
     */
    QuizCreationResponse create(QuizCreationRequest quiz);
}

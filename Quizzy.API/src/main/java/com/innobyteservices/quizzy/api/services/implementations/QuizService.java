package com.innobyteservices.quizzy.api.services.implementations;

import com.innobyteservices.quizzy.api.dto.response.QuizCreationResponse;
import com.innobyteservices.quizzy.api.dto.response.QuizResponse;
import com.innobyteservices.quizzy.api.entities.Quiz;
import com.innobyteservices.quizzy.api.repositories.interfaces.IQuizRepository;
import com.innobyteservices.quizzy.api.services.interfaces.IQuizService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Service implementation for quiz-related operations.
 * <p>
 * Delegates business logic to the quiz repository.
 */
@Service
public class QuizService implements IQuizService {

    private final IQuizRepository _repository;

    /**
     * Constructs a new {@code QuizService} with the given repository.
     *
     * @param repository the quiz repository used to access data layer operations
     */
    public QuizService(IQuizRepository repository) {
        _repository = repository;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Deletes a quiz by its ID using the repository layer.
     *
     * @param id the unique identifier of the quiz to delete
     */

    @Override
    public void delete(Integer id) {
        _repository.delete(id);
    }
    /**
     * Creates a new quiz after validating required fields and assigning metadata.
     * <p>
     * Validates quiz name, duration, total questions, topic ID, createdBy, and updatedBy fields.
     * Automatically sets createdAt and updatedAt timestamps.
     * Delegates the persistence operation to the repository layer.
     *
     * @param quiz the {@link Quiz} entity to be created
     * @return the ID of the newly created quiz
     * @throws IllegalArgumentException if any required quiz field is missing or invalid
     * @throws RuntimeException         if quiz creation fails at the repository level
     */

    @Override
    public QuizCreationResponse create(Quiz quiz) {
        // 1. Basic validation
        if (quiz.getId() == null) {
            throw new IllegalArgumentException("ID cannot be null.");
        }
        if (quiz.getName() == null || quiz.getName().isBlank()) {
            throw new IllegalArgumentException("Quiz name cannot be null or empty.");
        }
        if (quiz.getDuration() == null || quiz.getDuration() <= 0) {
            throw new IllegalArgumentException("Quiz duration must be greater than 0.");
        }
        if (quiz.getTotalQuestions() == null || !(quiz.getTotalQuestions() == 5 || quiz.getTotalQuestions() == 10)) {
            throw new IllegalArgumentException("Total questions must be either 5 or 10.");
        }
        if (quiz.getTopicId() == null) {
            throw new IllegalArgumentException("Topic ID cannot be null.");
        }
        if (quiz.getCreatedBy() == null) {
            throw new IllegalArgumentException("CreatedBy user ID cannot be null.");
        }
        if (quiz.getUpdatedBy() == null) {
            throw new IllegalArgumentException("UpdatedBy user ID cannot be null.");
        }

        // 2. Set timestamps
        Timestamp now = new Timestamp(System.currentTimeMillis());
        quiz.setCreatedAt(now);
        quiz.setUpdatedAt(now);

        QuizCreationResponse response = new QuizCreationResponse();
        response.setId(quizId);
        response.setName(quiz.getName());

        return response;
    }
}

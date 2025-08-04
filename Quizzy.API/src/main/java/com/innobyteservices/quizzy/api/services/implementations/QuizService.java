package com.innobyteservices.quizzy.api.services.implementations;

import com.innobyteservices.quizzy.api.repositories.interfaces.IQuizRepository;
import com.innobyteservices.quizzy.api.services.interfaces.IQuizService;
import org.springframework.stereotype.Service;

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
}

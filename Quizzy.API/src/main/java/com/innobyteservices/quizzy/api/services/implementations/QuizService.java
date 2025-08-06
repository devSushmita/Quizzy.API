package com.innobyteservices.quizzy.api.services.implementations;

import com.innobyteservices.quizzy.api.dto.request.QuizCreationRequest;
import com.innobyteservices.quizzy.api.dto.response.QuizCreationResponse;
import com.innobyteservices.quizzy.api.entities.Quiz;
import com.innobyteservices.quizzy.api.exceptions.InvalidRequestException;
import com.innobyteservices.quizzy.api.exceptions.QuizCreationFailedException;
import com.innobyteservices.quizzy.api.internals.CurrentUserContext;
import com.innobyteservices.quizzy.api.repositories.interfaces.IQuizRepository;
import com.innobyteservices.quizzy.api.services.interfaces.IQuizService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link IQuizService} interface that provides business logic
 * for quiz creation and deletion operations.
 * <p>
 * This service validates input data, maps DTOs to entities, sets metadata,
 * and delegates persistence to the repository layer.
 */
@Service
public class QuizService implements IQuizService {

    private final IQuizRepository _repository;
    private final ModelMapper _mapper;
    private final CurrentUserContext _context;

    /**
     * Constructs a new {@code QuizService} with the required dependencies.
     *
     * @param repository the quiz repository used for database operations
     * @param mapper     the model mapper used for DTO-to-entity conversions
     * @param context    the current user context for metadata population
     */
    public QuizService(IQuizRepository repository, ModelMapper mapper, CurrentUserContext context) {
        this._repository = repository;
        this._mapper = mapper;
        this._context = context;
    }

    /**
     * Deletes a quiz by its ID.
     *
     * @param id the unique identifier of the quiz to be deleted
     */
    @Override
    public void delete(Integer id) {
        _repository.delete(id);
    }

    /**
     * Creates a new quiz based on the given request data.
     *
     * @param request the quiz creation request object containing quiz details
     * @return the response containing the generated quiz ID
     * @throws InvalidRequestException     if the request object is null
     * @throws QuizCreationFailedException if quiz creation fails in the repository
     */
    @Override
    public QuizCreationResponse create(QuizCreationRequest request) {
        if (request == null) {
            throw new InvalidRequestException();
        }


        Quiz quiz = _mapper.map(request, Quiz.class);
        quiz.setCreatedBy(_context.getUserId());

        Integer quizId = _repository.create(quiz);

        if (quizId != null) {
            QuizCreationResponse response = _mapper.map(request, QuizCreationResponse.class);
            response.setId(quizId);
            return response;
        } else {
            throw new QuizCreationFailedException();
        }
    }
}

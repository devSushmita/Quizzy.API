package com.innobyteservices.quizzy.api.services.implementations;

import com.innobyteservices.quizzy.api.dto.request.QuestionCreationRequest;
import com.innobyteservices.quizzy.api.dto.response.QuestionCreationResponse;
import com.innobyteservices.quizzy.api.entities.Question;
import com.innobyteservices.quizzy.api.exceptions.InvalidRequestException;
import com.innobyteservices.quizzy.api.exceptions.QuestionCreationFailedException;
import com.innobyteservices.quizzy.api.internals.CurrentUserContext;
import com.innobyteservices.quizzy.api.repositories.interfaces.IQuestionRepository;
import com.innobyteservices.quizzy.api.services.interfaces.IQuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link IQuestionService} interface.
 * <p>
 * Provides business logic for creating quiz questions.
 * Handles input validation, DTO-to-entity mapping, and delegates persistence to the repository.
 */
@Service
public class QuestionService implements IQuestionService {

    private final IQuestionRepository _repository;
    private final ModelMapper _mapper;
    private final CurrentUserContext _context;

    /**
     * Constructs a new {@code QuestionService} with required dependencies.
     *
     * @param repository the question repository used for database operations
     * @param mapper     the model mapper used for object mapping
     * @param context    the current user context to extract user info
     */
    public QuestionService(IQuestionRepository repository, ModelMapper mapper, CurrentUserContext context) {
        _repository = repository;
        _mapper = mapper;
        _context = context;
    }

    /**
     * Creates a new question based on the provided request.
     * <p>
     * Validates the request, maps it to an entity, sets metadata,
     * persists it via repository, and returns the creation response.
     *
     * @param request the question creation request containing input data
     * @return the response with generated question ID and message
     * @throws InvalidRequestException        if the request is null
     * @throws QuestionCreationFailedException if the question could not be saved
     */
    @Override
    public QuestionCreationResponse create(QuestionCreationRequest request) {
        if (request == null) {
            throw new InvalidRequestException();
        }

        Question question = _mapper.map(request, Question.class);
        question.setCreatedBy(_context.getUserId());

        Integer questionId = _repository.create(question);

        if (questionId != null) {
            QuestionCreationResponse response = _mapper.map(request, QuestionCreationResponse.class);
            response.setId(questionId);
            return response;
        } else {
            throw new QuestionCreationFailedException();
        }
    }
}

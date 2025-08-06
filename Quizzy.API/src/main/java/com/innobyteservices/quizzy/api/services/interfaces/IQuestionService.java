package com.innobyteservices.quizzy.api.services.interfaces;

import com.innobyteservices.quizzy.api.dto.request.QuestionCreationRequest;
import com.innobyteservices.quizzy.api.dto.response.QuestionCreationResponse;

/**
 * Service interface for handling question-related business logic.
 */
public interface IQuestionService {

    /**
     * Creates a new question.
     *
     * @param request the request object containing question details
     * @return the response containing created question ID and info
     */
    QuestionCreationResponse create(QuestionCreationRequest request);
}

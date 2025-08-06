package com.innobyteservices.quizzy.api.exceptions;

import com.innobyteservices.quizzy.api.constants.ErrorMessage;
import com.innobyteservices.quizzy.api.enums.ErrorCode;

/**
 * Exception thrown when a quiz question fails to be created.
 * <p>
 * Typically used when the repository fails to persist the question or
 * validation fails at the service layer.
 */
public class QuestionCreationFailedException extends APIException {

    /**
     * Constructs a new {@code QuestionCreationFailedException} with a predefined
     * error message and error code indicating that question creation failed.
     */
    public QuestionCreationFailedException() {
        super(ErrorMessage.ERR_TOTAL_QUIZ_QUESTIONS_SHOULD_BE_GREATER_THAN_ZERO, ErrorCode.QUESTIONS_CREATION_FAILED);
    }
}

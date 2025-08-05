package com.innobyteservices.quizzy.api.exceptions;

import com.innobyteservices.quizzy.api.constants.ErrorMessage;
import com.innobyteservices.quizzy.api.enums.ErrorCode;

/**
 * Exception thrown when a quiz creation operation fails.
 *
 * <p>This exception is associated with the {@link ErrorCode#QUIZ_CREATION_FAILED}
 * error code and uses the predefined message from
 * {@link ErrorMessage#ERR_QUIZ_CREATION_FAILED}.</p>
 */
public class QuizCreationFailedException extends APIException {

    /**
     * Constructs a new {@code QuizCreationFailedException} with the
     * standard error message and error code.
     */
    public QuizCreationFailedException() {
        super(ErrorMessage.ERR_QUIZ_CREATION_FAILED, ErrorCode.QUIZ_CREATION_FAILED);
    }
}

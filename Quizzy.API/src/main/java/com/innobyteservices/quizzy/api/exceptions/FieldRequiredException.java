package com.innobyteservices.quizzy.api.exceptions;

import com.innobyteservices.quizzy.api.enums.ErrorCode;

/**
 * Thrown when a required field is missing or empty.
 */
public class FieldRequiredException extends APIException {

    /**
     * Creates the exception with a message and error code.
     *
     * @param message error message
     * @param code    validation error code
     */
    public FieldRequiredException(String message, ErrorCode code) {
        super(message, code);
    }
}

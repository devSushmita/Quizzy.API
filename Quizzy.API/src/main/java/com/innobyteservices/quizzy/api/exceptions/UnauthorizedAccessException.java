package com.innobyteservices.quizzy.api.exceptions;

import com.innobyteservices.quizzy.api.enums.ErrorCode;

/**
 * Thrown when a user tries to access a resource without proper authorization.
 *
 * Uses {@link ErrorCode#UNAUTHORIZED_ACCESS} as the error code.
 */
public class UnauthorizedAccessException extends APIException {

    /**
     * Creates an exception with a custom unauthorized access message.
     *
     * @param message error message
     */
    public UnauthorizedAccessException(String message) {
        super(message, ErrorCode.UNAUTHORIZED_ACCESS);
    }
}

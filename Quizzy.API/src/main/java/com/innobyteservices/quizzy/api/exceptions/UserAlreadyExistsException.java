package com.innobyteservices.quizzy.api.exceptions;

import com.innobyteservices.quizzy.api.constants.ErrorMessage;
import com.innobyteservices.quizzy.api.enums.ErrorCode;

/**
 * Thrown when a user with the given credentials already exists.
 */
public class UserAlreadyExistsException extends APIException {

    /**
     * Creates the exception with default message and error code.
     */
    public UserAlreadyExistsException() {
        super(ErrorMessage.ERR_USER_EXISTS, ErrorCode.USER_ALREADY_EXISTS);
    }
}

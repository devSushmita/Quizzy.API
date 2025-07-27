package com.innobyteservices.quizzy.api.exceptions;

import com.innobyteservices.quizzy.api.constants.ErrorMessage;
import com.innobyteservices.quizzy.api.enums.ErrorCode;

/**
 * Thrown when user login fails.
 */
public class LoginFailedException extends APIException {

    /**
     * Creates a login failure exception with default message and code.
     */
    public LoginFailedException() {
        super(ErrorMessage.ERR_LOGIN_FAILED, ErrorCode.USER_LOGIN_FAILED);
    }
}

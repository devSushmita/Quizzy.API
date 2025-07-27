package com.innobyteservices.quizzy.api.exceptions;

import com.innobyteservices.quizzy.api.constants.ErrorMessage;
import com.innobyteservices.quizzy.api.enums.ErrorCode;

/**
 * Thrown when the password doesn't meet strength requirements.
 */
public class StrongPasswordRequiredException extends APIException {

    /**
     * Creates an exception with default message and code for weak passwords.
     */
    public StrongPasswordRequiredException() {
        super(ErrorMessage.ERR_STRONG_PASSWORD, ErrorCode.STRONG_PASSWORD_REQUIRED);
    }
}

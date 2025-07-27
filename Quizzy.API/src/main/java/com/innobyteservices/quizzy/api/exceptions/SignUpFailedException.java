package com.innobyteservices.quizzy.api.exceptions;

import com.innobyteservices.quizzy.api.constants.ErrorMessage;
import com.innobyteservices.quizzy.api.enums.ErrorCode;

/**
 * Thrown when user sign-up fails.
 */
public class SignUpFailedException extends APIException {

    /**
     * Creates a sign-up failure exception with default message and code.
     */
    public SignUpFailedException() {
        super(ErrorMessage.ERR_SIGN_UP_FAILED, ErrorCode.SIGN_UP_FAILED);
    }
}

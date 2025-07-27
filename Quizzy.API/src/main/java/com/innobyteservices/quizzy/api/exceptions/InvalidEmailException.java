package com.innobyteservices.quizzy.api.exceptions;

import com.innobyteservices.quizzy.api.constants.ErrorMessage;
import com.innobyteservices.quizzy.api.enums.ErrorCode;

/**
 * Thrown when an email address is invalid.
 */
public class InvalidEmailException extends APIException {

    /**
     * Creates an exception for invalid email format.
     */
    public InvalidEmailException() {
        super(ErrorMessage.ERR_INVALID_EMAIL, ErrorCode.INVALID_EMAIL);
    }
}

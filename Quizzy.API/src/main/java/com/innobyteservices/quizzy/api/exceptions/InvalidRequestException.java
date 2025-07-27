package com.innobyteservices.quizzy.api.exceptions;

import com.innobyteservices.quizzy.api.constants.ErrorMessage;
import com.innobyteservices.quizzy.api.enums.ErrorCode;

/**
 * Thrown when a request is invalid or malformed.
 */
public class InvalidRequestException extends APIException {

    /**
     * Creates an exception indicating the request is invalid.
     */
    public InvalidRequestException() {
        super(ErrorMessage.ERR_INVALID_REQUEST, ErrorCode.INVALID_REQUEST);
    }
}

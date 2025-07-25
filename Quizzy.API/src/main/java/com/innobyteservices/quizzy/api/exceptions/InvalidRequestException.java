package com.innobyteservices.quizzy.api.exceptions;

import com.innobyteservices.quizzy.api.constants.ErrorMessage;
import com.innobyteservices.quizzy.api.enums.ErrorCode;

public class InvalidRequestException extends APIException {
    public InvalidRequestException() {
        super(ErrorMessage.ERR_INVALID_REQUEST, ErrorCode.INVALID_REQUEST);
    }
}

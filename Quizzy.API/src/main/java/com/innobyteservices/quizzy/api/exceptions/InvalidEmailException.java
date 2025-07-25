package com.innobyteservices.quizzy.api.exceptions;

import com.innobyteservices.quizzy.api.constants.ErrorMessage;
import com.innobyteservices.quizzy.api.enums.ErrorCode;

public class InvalidEmailException extends APIException {
    public InvalidEmailException() {
        super(ErrorMessage.ERR_INVALID_EMAIL, ErrorCode.INVALID_EMAIL);
    }
}
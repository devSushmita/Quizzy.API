package com.innobyteservices.quizzy.api.exceptions;

import com.innobyteservices.quizzy.api.constants.ErrorMessage;
import com.innobyteservices.quizzy.api.enums.ErrorCode;

public class StrongPasswordRequiredException extends APIException {
    public StrongPasswordRequiredException() {
        super(ErrorMessage.ERR_STRONG_PASSWORD, ErrorCode.STRONG_PASSWORD_REQUIRED);
    }
}

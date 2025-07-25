package com.innobyteservices.quizzy.api.exceptions;

import com.innobyteservices.quizzy.api.constants.ErrorMessage;
import com.innobyteservices.quizzy.api.enums.ErrorCode;

public class PasswordRequiredException extends APIException {
    public PasswordRequiredException() {
        super(ErrorMessage.ERR_PASSWORD_REQUIRED, ErrorCode.PASSWORD_REQUIRED);
    }
}

package com.innobyteservices.quizzy.api.exceptions;

import com.innobyteservices.quizzy.api.constants.ErrorMessage;
import com.innobyteservices.quizzy.api.enums.ErrorCode;

public class EmailRequiredException extends APIException {
    public EmailRequiredException() {
        super(ErrorMessage.ERR_EMAIL_REQUIRED, ErrorCode.EMAIL_REQUIRED);
    }
}

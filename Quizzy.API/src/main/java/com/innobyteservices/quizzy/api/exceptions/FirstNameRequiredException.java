package com.innobyteservices.quizzy.api.exceptions;

import com.innobyteservices.quizzy.api.constants.ErrorMessage;
import com.innobyteservices.quizzy.api.enums.ErrorCode;

public class FirstNameRequiredException extends APIException {
    public FirstNameRequiredException() {
        super(ErrorMessage.ERR_FIRSTNAME_REQUIRED, ErrorCode.FIRSTNAME_REQUIRED);
    }
}

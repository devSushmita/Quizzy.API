package com.innobyteservices.quizzy.api.exceptions;

import com.innobyteservices.quizzy.api.constants.ErrorMessage;
import com.innobyteservices.quizzy.api.enums.ErrorCode;

public class LastNameRequiredException extends APIException {
    public LastNameRequiredException() {
        super(ErrorMessage.ERR_LASTNAME_REQUIRED, ErrorCode.LASTNAME_REQUIRED);
    }
}

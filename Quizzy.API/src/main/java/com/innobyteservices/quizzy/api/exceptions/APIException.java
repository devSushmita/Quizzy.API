package com.innobyteservices.quizzy.api.exceptions;

import com.innobyteservices.quizzy.api.enums.ErrorCode;

public class APIException extends RuntimeException {

    public ErrorCode code;

    public APIException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }

}

package com.innobyteservices.quizzy.api.exceptions;

import com.innobyteservices.quizzy.api.enums.ErrorCode;
import lombok.Data;

@Data
public class APIException extends RuntimeException {

    private ErrorCode code;

    public APIException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }
}

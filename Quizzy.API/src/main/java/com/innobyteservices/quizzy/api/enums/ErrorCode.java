package com.innobyteservices.quizzy.api.enums;

public enum ErrorCode {

    INVALID_REQUEST(1000),
    FIRSTNAME_REQUIRED(1100),
    LASTNAME_REQUIRED(1101),
    EMAIL_REQUIRED(1102),
    INVALID_EMAIL(1103),
    PASSWORD_REQUIRED(1104),
    STRONG_PASSWORD_REQUIRED(1105);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
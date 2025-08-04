package com.innobyteservices.quizzy.api.enums;

import java.util.Optional;

/**
 * Enum representing various error codes used in the Quizzy API.
 *
 * Each constant corresponds to a specific validation or request error.
 */
public enum ErrorCode {

    /**
     * Generic error for internal server error.
     */
    INTERNAL_SERVER_ERROR(500),

    /**
     * Generic error indicating the request is invalid.
     */
    INVALID_REQUEST(1000),

    /**
     * Error indicating that the first name field is required.
     */
    FIRSTNAME_REQUIRED(1100),

    /**
     * Error indicating that the last name field is required.
     */
    LASTNAME_REQUIRED(1101),

    /**
     * Error indicating that the email field is required.
     */
    EMAIL_REQUIRED(1102),

    /**
     * Error indicating that the email format is invalid.
     */
    INVALID_EMAIL(1103),

    /**
     * Error indicating that the password field is required.
     */
    PASSWORD_REQUIRED(1104),

    /**
     * Error indicating that the password does not meet the strength requirements.
     */
    STRONG_PASSWORD_REQUIRED(1105),

    SIGN_UP_FAILED(1106),

    USER_ALREADY_EXISTS(1107),

    USER_LOGIN_FAILED(1108),

    CLAIM_NOT_FOUND(1109),

    TOPIC_ALREADY_EXISTS(1110),

    TOPIC_NAME_REQUIRED(1111);

    /**
     * Numeric representation of the error code.
     */
    private final int code;

    /**
     * Constructor to associate an integer code with each enum constant.
     *
     * @param code the numeric error code
     */
    ErrorCode(int code) {
        this.code = code;
    }

    /**
     * Returns the numeric code associated with the error.
     *
     * @return the integer error code
     */
    public int getCode() {
        return code;
    }
}

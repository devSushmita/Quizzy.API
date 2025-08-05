package com.innobyteservices.quizzy.api.enums;

/**
 * Enum representing various error codes used in the Quizzy API.
 *
 * Each constant corresponds to a specific validation or request error.
 */
public enum ErrorCode {

    /**
     * Error indicating unauthorized access to a protected resource.
     */
    UNAUTHORIZED_ACCESS(401),

    /**
     * Generic error for internal server failures.
     */
    INTERNAL_SERVER_ERROR(500),

    /**
     * Error indicating that the request is invalid or malformed.
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

    /**
     * Generic error indicating that the user sign-up process failed.
     */
    SIGN_UP_FAILED(1106),

    /**
     * Error indicating that a user with the given email already exists.
     */
    USER_ALREADY_EXISTS(1107),

    /**
     * Error indicating that the user login attempt failed.
     */
    USER_LOGIN_FAILED(1108),

    /**
     * Error indicating that a required token claim was not found.
     */
    CLAIM_NOT_FOUND(1109),

    /**
     * Error indicating that a topic creation fails.
     */
    TOPIC_CREATION_FAILED(1110),

    /**
     * Error indicating that the topic name is required.
     */
    TOPIC_NAME_REQUIRED(1111),

    /**
     * Error indicating that the quiz name is required.
     */
    QUIZ_NAME_REQUIRED(1112),

    /**
     * Error code indicating that the quiz duration must be greater than zero.
     */
    QUIZ_DURATION_SHOULD_BE_GREATER_THAN_ZERO(1113),

    /**
     * Error code indicating that the total number of quiz questions must be greater than zero.
     */
    TOTAL_QUIZ_QUESTIONS_SHOULD_BE_GREATER_THAN_ZERO(1114),

    /**
     * Error code indicating that the quiz creation fails.
     */
    QUIZ_CREATION_FAILED(1115),

    /**
     * Error code for when a topic with the same name already exists.
     */
    TOPIC_ALREADY_EXISTS(1116);

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

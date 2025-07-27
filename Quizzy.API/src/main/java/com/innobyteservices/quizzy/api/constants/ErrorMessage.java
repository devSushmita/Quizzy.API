package com.innobyteservices.quizzy.api.constants;

/**
 * Defines standardized error messages used throughout the Quizzy API application.
 *
 * <p>
 * These constants are used to provide consistent error messages across
 * validation, authentication, and general exception handling layers.
 * Centralizing error messages improves maintainability and readability.
 * </p>
 *
 * <p>
 * This is a utility class and should not be instantiated.
 * </p>
 */
public abstract class ErrorMessage {

    /** Error message for unexpected internal server failures. */
    public static final String ERR_INTERNAL_SERVER = "Something went wrong. Please try again later.";

    /** Error message for an invalid or missing request body. */
    public static final String ERR_INVALID_REQUEST = "Request body is either not given or invalid.";

    /** Error message when the first name is missing during sign-up. */
    public static final String ERR_FIRSTNAME_REQUIRED = "Firstname is required.";

    /** Error message when the last name is missing during sign-up. */
    public static final String ERR_LASTNAME_REQUIRED = "Lastname is required.";

    /** Error message when the email is missing during sign-up. */
    public static final String ERR_EMAIL_REQUIRED = "Email is required.";

    /** Error message when the email format is invalid or not found. */
    public static final String ERR_INVALID_EMAIL = "Given email format is invalid or not found.";

    /** Error message when the password is missing during sign-up. */
    public static final String ERR_PASSWORD_REQUIRED = "Password is required.";

    /** Error message when the password does not meet strength requirements. */
    public static final String ERR_STRONG_PASSWORD = "Given password is not a strong password.";

    /** Generic error message for sign-up failures. */
    public static final String ERR_SIGN_UP_FAILED = "Something went wrong. Sign up failed.";

    /** Error message when a user with the same email already exists. */
    public static final String ERR_USER_EXISTS = "User with the same email already exists.";

    /** Error message when login credentials are invalid. */
    public static final String ERR_LOGIN_FAILED = "Invalid email or password.";

    /** Error message when a required claim is not found. */
    public static final String ERR_CLAIM_NOT_FOUND = "Claim not found.";

    /**
     * Private constructor to prevent instantiation.
     */
    private ErrorMessage() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

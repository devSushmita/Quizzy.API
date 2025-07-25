package com.innobyteservices.quizzy.api.constants;

public class ErrorMessage {
    public static final String ERR_INVALID_REQUEST = "Request body is either not given or invalid.";

    public static final String ERR_FIRSTNAME_REQUIRED = "Firstname is required for the sign-up.";

    public static final String ERR_LASTNAME_REQUIRED = "Lastname is required for the sign-up.";

    public static final String ERR_EMAIL_REQUIRED = "Email is required for the sign-up";

    public static final String ERR_INVALID_EMAIL = "Given email format is invalid or not found.";

    public static final String ERR_PASSWORD_REQUIRED = "Password is required for the sign-up.";

    public static final String ERR_STRONG_PASSWORD = "Given password is not a strong password.";

}

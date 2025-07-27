package com.innobyteservices.quizzy.api.dto.response;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Represents the response returned after a successful login attempt.
 * <p>
 * Contains the generated access token, its expiry time, and user information.
 * </p>
 */
@Data
public class LoginResponse {

    /**
     * The JWT access token issued to the authenticated user.
     */
    private String accessToken;

    /**
     * The expiration date and time of the issued access token.
     */
    private Date expiresAt;

    /**
     * The user details associated with the authenticated session.
     */
    private UserResponse user;
}

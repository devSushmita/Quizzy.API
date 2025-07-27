package com.innobyteservices.quizzy.api.internals;

import lombok.Data;

import java.util.Date;

/**
 * Represents a JWT token along with its expiration information.
 * <p>
 * This class holds the token string and the date/time at which it expires.
 * Typically used as a response or internal representation after token generation.
 * </p>
 */
@Data
public class Token {

    /**
     * The JWT token value as a string.
     */
    private String value;

    /**
     * The expiration timestamp of the token.
     */
    private Date expiresAt;
}

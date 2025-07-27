package com.innobyteservices.quizzy.api.constants;

/**
 * Defines constant claim names used in JWT (JSON Web Token) payloads.
 * <p>
 * These constants represent the keys for user-related information embedded within
 * a JWT token. Using predefined keys ensures consistency when generating and parsing tokens.
 * </p>
 *
 * <p>This class is not meant to be instantiated.</p>
 */
public abstract class TokenClaimNames {

    /**
     * Claim name for the user's role.
     */
    public static final String Role = "role";

    /**
     * Claim name for the user's email address.
     */
    public static final String Email = "email";

    /**
     * Claim name for the user's unique ID.
     */
    public static final String UserId = "userId";

    // Private constructor to prevent instantiation
    private TokenClaimNames() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

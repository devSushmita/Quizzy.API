package com.innobyteservices.quizzy.api.services.interfaces;

import com.innobyteservices.quizzy.api.enums.ClaimType;
import com.innobyteservices.quizzy.api.internals.Token;
import com.innobyteservices.quizzy.api.internals.TokenPayload;

import java.util.Map;

/**
 * Service interface for handling JWT operations.
 */
public interface ITokenService {

    /**
     * Generates a JWT for the given user payload.
     *
     * @param payload user data to include in the token
     * @return generated token with value and expiry
     */
    Token generateToken(TokenPayload payload);

    /**
     * Extracts claims from a token.
     *
     * @param token     the JWT string
     * @param claimType type of claim to extract
     * @return extracted claims as a map
     */
    Map<String, Object> getClaim(String token, ClaimType claimType);

    /**
     * Checks if a token is expired.
     *
     * @param token the JWT string
     * @return true if expired; false otherwise
     */
    boolean isTokenExpired(String token);
}

package com.innobyteservices.quizzy.api.enums;

/**
 * Defines the types of claims that can be embedded in a JWT (JSON Web Token).
 * <p>
 * Each claim type corresponds to a specific piece of user-related information
 * and is associated with a unique integer code.
 * </p>
 */
public enum ClaimType {

    /** All available claims. */
    All(0),

    /** Claim for the user's unique identifier. */
    UserId(1),

    /** Claim for the user's email address. */
    Email(2),

    /**
     * Claim for the user's password.
     * <p><b>Warning:</b> Including passwords in tokens is not recommended due to security risks.</p>
     */
    Role(3);

    /**
     * Numeric code associated with the question level.
     */
    private final int code;

    /**
     * Creates a new claim type with the specified code.
     *
     * @param code the unique code for the claim type
     */
    ClaimType(int code) {
        this.code = code;
    }

    /**
     * Gets the integer code for the claim type.
     *
     * @return the code associated with this claim type
     */
    public int getCode() {
        return code;
    }
}

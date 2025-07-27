package com.innobyteservices.quizzy.api.enums;

/**
 * Enum representing the types of claims that can be included in a JWT (JSON Web Token).
 * Each claim is associated with a specific integer code.
 */
public enum ClaimType {

    /** Represents all available claims. */
    All(0),

    /** Claim for the user's ID. */
    UserId(1),

    /** Claim for the user's email. */
    Email(2),

    /**
     * Claim for the user's role.
     */
    Role(3);

    /** Numeric code associated with the claim type. */
    private final int value;

    /**
     * Constructor to assign a code to the claim type.
     *
     * @param value The integer code.
     */
    ClaimType(int value) {
        this.value = value;
    }

    /**
     * Returns the code associated with the claim type.
     *
     * @return Integer value of the claim.
     */
    public int getValue() {
        return value;
    }
}

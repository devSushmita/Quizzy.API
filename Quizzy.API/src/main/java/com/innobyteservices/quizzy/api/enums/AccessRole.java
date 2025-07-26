package com.innobyteservices.quizzy.api.enums;

/**
 * Represents different access roles that can be assigned to users in the system.
 *
 * <p>Each role is associated with a specific integer value for persistence or comparison purposes.</p>
 */
public enum AccessRole {

    /**
     * No role assigned (default).
     */
    None(0),

    /**
     * Administrator role with the highest level of access.
     */
    Administrator(1),

    /**
     * Regular user role with limited access.
     */
    User(2);

    /**
     * The integer value associated with the role.
     */
    private final int value;

    /**
     * Constructs an {@code AccessRole} with the specified integer value.
     *
     * @param value the integer value representing the role
     */
    AccessRole(int value) {
        this.value = value;
    }

    /**
     * Returns the integer value associated with this access role.
     *
     * @return the integer value of the role
     */
    public int getValue() {
        return value;
    }
}

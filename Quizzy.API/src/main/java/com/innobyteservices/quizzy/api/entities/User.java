package com.innobyteservices.quizzy.api.entities;

import com.innobyteservices.quizzy.api.enums.AccessRole;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Represents a user in the system with authentication and role details.
 */
@Data
public class User {

    /**
     * Unique identifier for the entity.
     */
    private int id;

    /**
     * User's first name.
     */
    private String firstname;

    /**
     * User's last name.
     */
    private String lastname;

    /**
     * Role assigned to the user.
     */
    private AccessRole role;

    /**
     * User's email address.
     */
    private String email;

    /**
     * Encrypted password of the user.
     */
    private String password;

    /**
     * Timestamp when the user was created.
     */
    private Timestamp createdAt;

    /**
     * Timestamp when the user was last updated.
     */
    private Timestamp updatedAt;
}

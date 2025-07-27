package com.innobyteservices.quizzy.api.internals;

import com.innobyteservices.quizzy.api.enums.AccessRole;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents the payload content stored in a JWT (JSON Web Token).
 * <p>
 * This class contains essential user-related information that can be
 * embedded inside a JWT for authentication and authorization purposes.
 * </p>
 */
@Getter
@Setter
public class TokenPayload {

    /**
     * The unique identifier of the user.
     */
    private int userId;

    /**
     * The email address associated with the user.
     */
    private String email;

    /**
     * The access role assigned to the user (e.g., ADMIN, USER).
     */
    private AccessRole role;
}

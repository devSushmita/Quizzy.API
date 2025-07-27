package com.innobyteservices.quizzy.api.dto.response;

import lombok.Data;

/**
 * Response returned after a successful user sign-up.
 */
@Data
public class SignUpResponse {
    /**
     * ID of the newly registered user.
     */
    private int id;
}

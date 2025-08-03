package com.innobyteservices.quizzy.api.internals;

import lombok.Data;

/**
 * Filter options for querying users by optional fields like ID or email.
 * Fields set to null are ignored during filtering.
 */
@Data
public class UserFilter {

    /**
     * Filter by user ID (optional).
     */
    private Integer id;

    /**
     * Filter by email address (optional).
     */
    private String email;
}

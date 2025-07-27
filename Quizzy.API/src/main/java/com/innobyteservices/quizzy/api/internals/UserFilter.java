package com.innobyteservices.quizzy.api.internals;

import lombok.Data;

import java.util.Optional;

/**
 * Filter criteria for querying User entities.
 * <p>
 * This class allows filtering based on optional fields such as user ID and email.
 * When a field is empty, it is excluded from the query.
 * </p>
 */
@Data
public class UserFilter {

    /**
     * Optional user ID for filtering.
     */
    private Optional<Integer> id;

    /**
     * Optional email address for filtering.
     */
    private Optional<String> email;
}

package com.innobyteservices.quizzy.api.repositories.interfaces;

import com.innobyteservices.quizzy.api.internals.UserFilter;
import com.innobyteservices.quizzy.api.entities.User;

/**
 * Defines user-related data access operations.
 *
 * <p>
 * Provides methods to create a user and retrieve user details based on filter criteria.
 * </p>
 */
public interface IUserRepository {

    /**
     * Inserts a new user into the data source.
     *
     * @param user the user entity to be created
     * @return the generated user ID, or {@code null} if creation fails
     */
    Integer create(User user);

    /**
     * Retrieves a user matching the specified filter.
     *
     * @param filter the filter containing search criteria like ID or email
     * @return the matching user, or {@code null} if not found
     */
    User get(UserFilter filter);
}

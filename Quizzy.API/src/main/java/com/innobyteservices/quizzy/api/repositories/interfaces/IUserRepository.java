package com.innobyteservices.quizzy.api.repositories.interfaces;

import com.innobyteservices.quizzy.api.internals.UserFilter;
import com.innobyteservices.quizzy.api.entities.User;

import java.util.Optional;

/**
 * Defines the contract for user-related data operations.
 * <p>
 * Provides abstraction for creating users and retrieving user details
 * based on dynamic filter criteria.
 * </p>
 */
public interface IUserRepository {

    /**
     * Persists a new {@link User} in the data source.
     *
     * @param user the {@link User} entity to be created
     * @return an {@link Optional} containing the generated user ID if the operation succeeds,
     *         or {@link Optional#empty()} if creation fails
     */
    Optional<Integer> create(User user);

    /**
     * Retrieves a {@link User} based on the given {@link UserFilter} criteria.
     *
     * @param filter the filter object containing lookup conditions such as email or ID
     * @return an {@link Optional} containing the matching user if found,
     *         or {@link Optional#empty()} if no match is found
     */
    Optional<User> get(UserFilter filter);
}

package com.innobyteservices.quizzy.api.repositories.interfaces;

import com.innobyteservices.quizzy.api.entities.Topic;

/**
 * Repository interface for topic-related data operations.
 * <p>
 * Provides functionality to add a new topic to the data source.
 */
public interface ITopicRepository {

    /**
     * Inserts a new topic into the data source.
     *
     * @param topic the topic entity to add
     * @return the generated topic ID, or {@code null} if the operation fails
     */
    Integer add(Topic topic);
}

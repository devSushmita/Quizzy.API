package com.innobyteservices.quizzy.api.repositories.interfaces;

import com.innobyteservices.quizzy.api.entities.Topic;
import java.util.List;

/**
 * Defines data access operations for {@link Topic} entities.
 * <p>
 * Provides methods to add and retrieve topics from the underlying data store.
 */
public interface ITopicRepository {

    /**
     * Adds a new topic to the data store.
     *
     * @param topic the topic entity to persist
     * @return the generated topic ID if successful; {@code null} otherwise
     */
    Integer add(Topic topic);

    /**
     * Retrieves all topics from the data store.
     *
     * @return a list of {@link Topic} entities
     */
    List<Topic> get();
}

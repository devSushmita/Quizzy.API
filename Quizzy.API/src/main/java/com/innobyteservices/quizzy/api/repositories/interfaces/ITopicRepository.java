package com.innobyteservices.quizzy.api.repositories.interfaces;

import com.innobyteservices.quizzy.api.entities.Topic;
import java.util.List;

/**
 * Repository interface for accessing and managing Topic entities.
 */
public interface ITopicRepository {

    /**
     * Retrieves all topics from the data source.
     *
     * @return a list of all {@link Topic} entities.
     */
    List<Topic> get();
}

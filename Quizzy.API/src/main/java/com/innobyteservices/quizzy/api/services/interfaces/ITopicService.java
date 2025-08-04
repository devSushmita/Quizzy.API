package com.innobyteservices.quizzy.api.services.interfaces;

import com.innobyteservices.quizzy.api.dto.response.TopicResponse;

import java.util.List;

/**
 * Service interface for managing quiz topics.
 * <p>
 * Declares operations related to retrieving topic data.
 * </p>
 */
public interface ITopicService {

    /**
     * Retrieves a list of all available quiz topics.
     *
     * @return a list of {@link TopicResponse} objects
     */
    List<TopicResponse> get();
}

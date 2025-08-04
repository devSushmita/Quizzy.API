package com.innobyteservices.quizzy.api.services.interfaces;

import com.innobyteservices.quizzy.api.dto.request.TopicCreationRequest;
import com.innobyteservices.quizzy.api.dto.response.TopicCreationResponse;

/**
 * Service interface for managing topics.
 * <p>
 * Provides functionality to add new topics.
 */
public interface ITopicService {

    /**
     * Adds a new topic based on the provided request data.
     *
     * @param request the topic creation request
     * @return the response containing the generated topic ID
     */
    TopicCreationResponse add(TopicCreationRequest request);
}

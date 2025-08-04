package com.innobyteservices.quizzy.api.services.interfaces;

import com.innobyteservices.quizzy.api.dto.request.TopicCreationRequest;
import com.innobyteservices.quizzy.api.dto.response.TopicCreationResponse;
import com.innobyteservices.quizzy.api.dto.response.TopicResponse;

import java.util.List;

/**
 * Service interface for managing quiz topics.
 * <p>
 * Declares operations for creating and retrieving topics.
 * </p>
 */
public interface ITopicService {

    /**
     * Adds a new topic using the provided request data.
     *
     * @param request the topic creation request containing topic details
     * @return a response containing the generated topic ID and name
     */
    TopicCreationResponse add(TopicCreationRequest request);

    /**
     * Retrieves all topics available in the system.
     *
     * @return a list of topic response objects
     */
    List<TopicResponse> get();
}

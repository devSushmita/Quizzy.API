package com.innobyteservices.quizzy.api.dto.response;

import lombok.Data;

/**
 * Response DTO returned after successfully creating a topic.
 * <p>
 * Contains the generated topic ID.
 */
@Data
public class TopicCreationResponse {
    /**
     * The unique identifier of the newly created topic.
     */
    private Integer id;
}

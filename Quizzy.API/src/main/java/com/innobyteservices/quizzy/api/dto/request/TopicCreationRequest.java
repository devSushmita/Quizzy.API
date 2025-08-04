package com.innobyteservices.quizzy.api.dto.request;

import com.innobyteservices.quizzy.api.annotations.Required;
import lombok.Data;

/**
 * Request DTO for creating a new topic.
 * <p>
 * Contains the name of the topic to be created.
 * </p>
 */
@Data
public class TopicCreationRequest {
    /**
     * The name of the topic.
     */
    @Required(fieldName = "topic_name")
    private String name;
}

package com.innobyteservices.quizzy.api.dto.response;

import lombok.Data;

/**
 * Represents the response object for a Topic, containing only minimal details.
 */
@Data
public class TopicResponse {
    private Integer id;
    private String name;
}

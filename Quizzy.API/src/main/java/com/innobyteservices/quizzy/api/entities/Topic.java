package com.innobyteservices.quizzy.api.entities;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Represents a quiz topic entity.
 * <p>
 * Contains metadata such as creation and update timestamps,
 * along with user IDs responsible for those actions.
 */
@Data
public class Topic {

    /**
     * The unique identifier of the topic.
     */
    private Integer id;

    /**
     * The name of the topic.
     */
    private String name;

    /**
     * The timestamp when the topic was created.
     */
    private Timestamp createdAt;

    /**
     * The timestamp when the topic was last updated.
     */
    private Timestamp updatedAt;

    /**
     * The user ID of the creator.
     */
    private Integer createdBy;

    /**
     * The user ID of the last person who updated the topic.
     */
    private Integer updatedBy;
}

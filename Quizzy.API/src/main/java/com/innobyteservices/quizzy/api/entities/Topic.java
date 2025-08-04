package com.innobyteservices.quizzy.api.entities;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Represents a quiz topic within the Quizzy application.
 * Contains metadata such as name, creation and update timestamps, and user references.
 */
@Data
public class Topic {

    /** Unique identifier for the topic. */
    private Integer id;

    /** Name or title of the topic. */
    private String name;

    /** Timestamp when the topic was created. */
    private Timestamp createdAt;

    /** Timestamp when the topic was last updated. */
    private Timestamp updatedAt;

    /** ID of the user who created the topic. */
    private Integer createdBy;

    /** ID of the user who last updated the topic. */
    private Integer updatedBy;
}

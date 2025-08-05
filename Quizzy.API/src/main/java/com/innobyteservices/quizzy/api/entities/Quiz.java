package com.innobyteservices.quizzy.api.entities;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Represents a quiz entity with all essential metadata such as name, topic, duration,
 * total number of questions, and audit information.
 *
 * <p>This class is typically used for persisting or retrieving quiz records
 * from the data source.</p>
 */
@Data
public class Quiz {

    /** The unique identifier for the quiz. */
    private Integer id;

    /** The name or title of the quiz. */
    private String name;

    /** The identifier of the topic associated with the quiz. */
    private Integer topicId;

    /** Indicates whether the quiz is marked as deleted. */
    private Boolean isDeleted;

    /** The total duration of the quiz in seconds. */
    private Integer duration;

    /** The total number of questions included in the quiz. */
    private Integer totalQuestions;

    /** The ID of the user who created the quiz. */
    private Integer createdBy;

    /** The ID of the user who last updated the quiz. */
    private Integer updatedBy;

    /** The timestamp when the quiz was created. */
    private Timestamp createdAt;

    /** The timestamp when the quiz was last updated. */
    private Timestamp updatedAt;
}

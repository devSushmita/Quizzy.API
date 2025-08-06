package com.innobyteservices.quizzy.api.entities;

import com.innobyteservices.quizzy.api.dto.response.OptionResponse;

import java.sql.Timestamp;
import java.util.List;

/**
 * Represents a quiz question entity.
 * <p>
 * Contains question metadata such as title, level, marks, associated topic ID,
 * audit timestamps, creator info, and response options.
 */
public class Question {

    /**
     * The unique identifier of the question.
     */
    private Integer id;

    /**
     * The title or text of the question.
     */
    private String title;

    /**
     * The difficulty level of the question.
     */
    private Integer level;

    /**
     * The marks awarded for correctly answering the question.
     */
    private Integer marks;

    /**
     * The ID of the topic this question belongs to.
     */
    private Integer topicId;

    /**
     * Timestamp indicating when the question was created.
     */
    private Timestamp createdAt;

    /**
     * Timestamp indicating when the question was last updated.
     */
    private Timestamp updatedAt;

    /**
     * The ID of the user who created the question.
     */
    private Integer createdBy;

    /**
     * The ID of the user who last updated the question.
     */
    private Integer updatedBy;

    /**
     * The list of response options associated with the question.
     */
    private List<OptionResponse> responses;
}

package com.innobyteservices.quizzy.api.entities;

import java.sql.Timestamp;

/**
 * Represents a quiz entity containing basic quiz details such as
 * name, duration, total number of questions, creator, and ID.
 */
public class Quiz {

    private Integer id;

    private String name;

    private Integer topicId;

    private Boolean Void;

    private Integer duration;

    private Integer totalQuestions;

    private Integer createdBy;

    private Integer updatedBy;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}

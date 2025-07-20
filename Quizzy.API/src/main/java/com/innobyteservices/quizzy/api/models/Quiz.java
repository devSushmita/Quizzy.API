package com.innobyteservices.quizzy.api.models;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Quiz {
    private int id;
    private String name;
    private int topicId;
    private boolean active;
    private int duration;
    private int totalQuestions;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int createdBy;
    private int updatedBy;
}

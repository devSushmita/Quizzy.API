package com.innobyteservices.quizzy.api.models;

import com.innobyteservices.quizzy.api.enums.QuestionLevel;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Question {
    private int id;
    private String title;
    private QuestionLevel level;
    private int marks;
    private int answerId ;
    private int topicId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int createdBy;
    private int updatedBy;
}

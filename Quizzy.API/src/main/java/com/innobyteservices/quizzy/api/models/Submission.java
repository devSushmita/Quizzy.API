package com.innobyteservices.quizzy.api.models;

import com.innobyteservices.quizzy.api.enums.SubmissionStatus;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Submission {
    private int id;
    private int userId;
    private int quizId;
    private int attempt;
    private String configuration;
    private String response;
    private Timestamp startedAt;
    private Timestamp submittedAt;
    private Timestamp updatedAt;
    private SubmissionStatus status;
    private int totalMarks;
}

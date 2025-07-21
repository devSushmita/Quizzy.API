package com.innobyteservices.quizzy.api.dto.response;

import java.sql.Timestamp;

import com.innobyteservices.quizzy.api.enums.SubmissionStatus;

import lombok.Data;

@Data

public class SubmissionHistory {
    private int id;
    private int userId;
    private int score;
    private int totalMarks;
    private SubmissionStatus status;
    private Timestamp startedAt;
    private Timestamp submittedAt;
    private Quiz quiz;
}

package com.innobyteservices.quizzy.api.dto.request;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.innobyteservices.quizzy.api.enums.SubmissionStatus;

import lombok.Data;

@Data
public class SubmissionRequest {
    private Timestamp completedAt;
    private SubmissionStatus status;
    private ArrayList<AttemptedQuestion> response;
}

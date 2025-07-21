package com.innobyteservices.quizzy.api.dto.response;

import java.util.ArrayList;

import lombok.Data;

@Data
public class SubmissionResponse {
    private int submissionId;
    private int score;
    private int totalMarks;
    private ArrayList<ScoreCardItem> scoreCard;
}

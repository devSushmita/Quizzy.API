package com.innobyteservices.quizzy.api.dto.request;

import com.innobyteservices.quizzy.api.enums.QuestionLevel;
import lombok.Data;

import java.util.ArrayList;

@Data
public class QuestionCreationRequest {
    private String title;
    private QuestionLevel level;
    private int marks;
    private int answer;
    private int topicId;
    private ArrayList<String> options;
}

package com.innobyteservices.quizzy.api.dto.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AttemptResponse {
    private int id;
    private int quizId;
    private int duration;
    private String configuration;
    private ArrayList<Question> questions;
}

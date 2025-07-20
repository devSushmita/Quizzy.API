package com.innobyteservices.quizzy.api.dto.response;

import java.util.ArrayList;

public class Question {
    private int id;
    private String title;
    private int marks;
    private ArrayList<Option> options;
}

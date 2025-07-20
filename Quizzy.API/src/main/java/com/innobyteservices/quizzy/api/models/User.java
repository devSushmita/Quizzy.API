package com.innobyteservices.quizzy.api.models;

import com.innobyteservices.quizzy.api.enums.AccessRole;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    private int id;
    private String firstname;
    private String lastname;
    private AccessRole role;
    private String email;
    private String password;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}

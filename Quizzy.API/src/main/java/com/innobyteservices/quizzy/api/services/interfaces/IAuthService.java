package com.innobyteservices.quizzy.api.services.interfaces;

import com.innobyteservices.quizzy.api.dto.request.LoginRequest;
import com.innobyteservices.quizzy.api.dto.request.SignUpRequest;
import com.innobyteservices.quizzy.api.dto.response.LoginResponse;
import com.innobyteservices.quizzy.api.dto.response.SignUpResponse;

public interface IAuthService {

    public SignUpResponse signUp(SignUpRequest request);

    public LoginResponse login(LoginRequest request);

}

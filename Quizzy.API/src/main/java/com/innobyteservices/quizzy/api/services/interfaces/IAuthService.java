package com.innobyteservices.quizzy.api.services.interfaces;

import com.innobyteservices.quizzy.api.dto.request.LoginRequest;
import com.innobyteservices.quizzy.api.dto.request.SignUpRequest;
import com.innobyteservices.quizzy.api.dto.response.LoginResponse;
import com.innobyteservices.quizzy.api.dto.response.SignUpResponse;

/**
 * Service for user authentication and registration.
 */
public interface IAuthService {

    /**
     * Registers a new user.
     *
     * @param request sign-up data
     * @return sign-up response
     */
    SignUpResponse signup(SignUpRequest request);

    /**
     * Logs in a user.
     *
     * @param request login credentials
     * @return login response with token
     */
    LoginResponse login(LoginRequest request);
}

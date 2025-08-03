package com.innobyteservices.quizzy.api.controllers;

import com.innobyteservices.quizzy.api.dto.request.LoginRequest;
import com.innobyteservices.quizzy.api.dto.request.SignUpRequest;
import com.innobyteservices.quizzy.api.dto.response.APIResponse;
import com.innobyteservices.quizzy.api.dto.response.LoginResponse;
import com.innobyteservices.quizzy.api.dto.response.SignUpResponse;
import com.innobyteservices.quizzy.api.services.interfaces.IAuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling user authentication operations.
 * Provides endpoints for user sign-up and login.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final IAuthService _service;

    /**
     * Creates an instance of AuthController with the given auth service.
     *
     * @param service the authentication service implementation
     */
    public AuthController(IAuthService service) {
        this._service = service;
    }

    /**
     * Registers a new user.
     *
     * @param request the sign-up details
     * @return API response with registration result
     */
    @PostMapping("/signup")
    public ResponseEntity<APIResponse<SignUpResponse>> signup(@Valid @RequestBody SignUpRequest request) {
        SignUpResponse data = _service.signup(request);
        APIResponse<SignUpResponse> apiResponse = new APIResponse<>();
        apiResponse.setData(data);
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Authenticates a user and returns login details.
     *
     * @param request the login credentials
     * @return API response with JWT token and user info
     */
    @PostMapping("/login")
    public ResponseEntity<APIResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse data = _service.login(request);
        APIResponse<LoginResponse> apiResponse = new APIResponse<>();
        apiResponse.setData(data);
        return ResponseEntity.ok(apiResponse);
    }
}

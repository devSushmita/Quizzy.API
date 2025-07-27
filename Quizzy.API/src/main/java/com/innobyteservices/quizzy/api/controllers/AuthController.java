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

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final IAuthService _service;

    public AuthController(IAuthService service) {
        this._service = service;
    }

    /**
     * Handles user sign-up.
     *
     * @param request sign-up request containing user details
     * @return success message
     */
    @PostMapping("/signup")
    public ResponseEntity<APIResponse<SignUpResponse>> signup(@Valid @RequestBody SignUpRequest request) {
        SignUpResponse data = _service.signup(request);
        APIResponse<SignUpResponse> apiResponse = new APIResponse<>();
        apiResponse.setData(data);
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Handles user login.
     *
     * @param request login request containing email and password
     * @return JWT and user details
     */
    @PostMapping("/login")
    public ResponseEntity<APIResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse data = _service.login(request);
        APIResponse<LoginResponse> apiResponse = new APIResponse<>();
        apiResponse.setData(data);
        return ResponseEntity.ok(apiResponse);
    }
}

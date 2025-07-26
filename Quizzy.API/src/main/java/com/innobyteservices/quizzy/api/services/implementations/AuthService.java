package com.innobyteservices.quizzy.api.services.implementations;

import com.innobyteservices.quizzy.api.dto.request.LoginRequest;
import com.innobyteservices.quizzy.api.dto.request.SignUpRequest;
import com.innobyteservices.quizzy.api.dto.response.LoginResponse;
import com.innobyteservices.quizzy.api.dto.response.SignUpResponse;
import com.innobyteservices.quizzy.api.exceptions.*;
import com.innobyteservices.quizzy.api.services.interfaces.IAuthService;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.AddressException;

public class AuthService implements IAuthService {

    @Override
    public SignUpResponse signUp(SignUpRequest request) {
        if (request == null) {
            throw new InvalidRequestException();
        }

        if (request.getFirstname() == null || request.getFirstname().isBlank()) {
            throw new FirstNameRequiredException();
        }

        if (request.getLastname() == null || request.getLastname().isBlank()) { 
            throw new LastNameRequiredException();
        }

        if (request.getEmail() == null || request.getEmail().isBlank()) {   
            throw new EmailRequiredException();
        }

        if (!isValidEmail(request.getEmail())) {
            throw new InvalidEmailException();
        }

        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new PasswordRequiredException();
        }

        if (!isStrongPassword(request.getPassword())) {
            throw new StrongPasswordRequiredException();
        }

        return null;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        if (request == null) {
            throw new UnsupportedOperationException("Unimplemented method 'signUp'");
        }

        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new EmailRequiredException();
        }

        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new PasswordRequiredException();
        }

        return null;
    }

    private static boolean isValidEmail(String email) {
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
            return true;
        } catch (AddressException ex) {
            return false;
        }
    }

    private static boolean isStrongPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUppercase = true;
            else if (Character.isLowerCase(ch)) hasLowercase = true;
            else if (Character.isDigit(ch)) hasDigit = true;
            else if ("!@#$%^&*()-_=+[]{}|;:'\",.<>?/\\`~".indexOf(ch) != -1) hasSpecialChar = true;
        }

        return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
    }
    
}

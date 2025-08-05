package com.innobyteservices.quizzy.api.advices;

import com.innobyteservices.quizzy.api.constants.ErrorMessage;
import com.innobyteservices.quizzy.api.dto.response.APIResponse;
import com.innobyteservices.quizzy.api.dto.response.ErrorResponse;
import com.innobyteservices.quizzy.api.enums.ErrorCode;
import com.innobyteservices.quizzy.api.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Global exception handler for handling application-level exceptions across all controllers.
 * Provides structured API error responses based on the exception type.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles all uncaught exceptions and delegates to a method
     * that builds a standardized API response.
     *
     * @param ex The thrown exception.
     * @return A ResponseEntity containing an {@link APIResponse} with error details.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Object>> handleException(Exception ex) {
        return getResponseBody(ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<Object>> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        Optional<Map.Entry<String, String>> firstEntryOpt = errors.entrySet().stream().findFirst();

        ErrorCode errorCode = ErrorCode.INVALID_REQUEST;
        String message = ErrorMessage.ERR_INVALID_REQUEST;

        if (firstEntryOpt.isPresent()) {
            String field = firstEntryOpt.get().getKey().toLowerCase();
            String strErrorCode = firstEntryOpt.get().getValue().toLowerCase();
            String requestURI = request.getRequestURI();
            String requestMethod = request.getMethod();

            switch (field) {
                case "firstname" -> {
                    errorCode = ErrorCode.FIRSTNAME_REQUIRED;
                    message = ErrorMessage.ERR_FIRSTNAME_REQUIRED;
                }
                case "lastname" -> {
                    errorCode = ErrorCode.LASTNAME_REQUIRED;
                    message = ErrorMessage.ERR_LASTNAME_REQUIRED;
                }
                case "email" -> {
                    if (strErrorCode.equals(String.valueOf(ErrorCode.EMAIL_REQUIRED.getCode()))) {
                        errorCode = ErrorCode.EMAIL_REQUIRED;
                        message = ErrorMessage.ERR_EMAIL_REQUIRED;
                    }
                    else {
                        errorCode = ErrorCode.INVALID_EMAIL;
                        message = ErrorMessage.ERR_INVALID_EMAIL;
                    }
                }
                case "password" -> {
                    if (strErrorCode.equals(String.valueOf(ErrorCode.STRONG_PASSWORD_REQUIRED.getCode()))) {
                        errorCode = ErrorCode.STRONG_PASSWORD_REQUIRED;
                        message = ErrorMessage.ERR_STRONG_PASSWORD;
                    }
                    else {
                        errorCode = ErrorCode.PASSWORD_REQUIRED;
                        message = ErrorMessage.ERR_PASSWORD_REQUIRED;
                    }
                }
                case "duration" -> {
                    errorCode = ErrorCode.QUIZ_DURATION_SHOULD_BE_GREATER_THAN_ZERO;
                    message = ErrorMessage.ERR_QUIZ_DURATION_SHOULD_BE_GREATER_THAN_ZERO;
                }
                case "totalquestions" -> {
                    errorCode = ErrorCode.TOTAL_QUIZ_QUESTIONS_SHOULD_BE_GREATER_THAN_ZERO;
                    message = ErrorMessage.ERR_TOTAL_QUIZ_QUESTIONS_SHOULD_BE_GREATER_THAN_ZERO;
                }
                case "name" -> {
                    if(requestMethod.equals("POST")) {
                        if (requestURI.equals("/api/topics")) {
                            errorCode = ErrorCode.TOPIC_NAME_REQUIRED;
                            message = ErrorMessage.ERR_TOPIC_NAME_REQUIRED;
                        }
                        if(requestURI.equals("/api/quizzes")) {
                            errorCode = ErrorCode.QUIZ_NAME_REQUIRED;
                            message = ErrorMessage.ERR_QUIZ_NAME_REQUIRED;
                        }
                    }
                }
            }
        }

        ErrorResponse error = new ErrorResponse();
        error.setCode(errorCode.getCode());
        error.setMessage(message);

        APIResponse<Object> response = new APIResponse<>();
        response.setError(error);

        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Builds the API response based on the exception type.
     * Determines the appropriate HTTP status code and error structure.
     *
     * @param ex The exception to process.
     * @return A ResponseEntity with appropriate status and error information.
     */
    private ResponseEntity<APIResponse<Object>> getResponseBody(Exception ex) {
        APIResponse<Object> response = new APIResponse<>();
        ErrorResponse error = new ErrorResponse();
        HttpStatus status = null;

        if (ex instanceof APIException apiEx) {
            if (ex instanceof ClaimNotFoundException) {
                status = HttpStatus.NOT_FOUND;
            }
            else if (
                    ex instanceof FieldRequiredException ||
                            ex instanceof InvalidEmailException ||
                            ex instanceof InvalidRequestException ||
                            ex instanceof SignUpFailedException ||
                            ex instanceof StrongPasswordRequiredException
            ) {
                status = HttpStatus.BAD_REQUEST;
            }
            else if (ex instanceof LoginFailedException) {
                status = HttpStatus.UNAUTHORIZED;
            }
            else if (ex instanceof UserAlreadyExistsException) {
                status = HttpStatus.CONFLICT;
            }
            else {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }

            error.setCode(apiEx.getCode().getCode());
            error.setMessage(apiEx.getMessage());
        } else {
            error.setCode(ErrorCode.INTERNAL_SERVER_ERROR.getCode());
            error.setMessage(ErrorMessage.ERR_INTERNAL_SERVER);
        }

        response.setError(error);

        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(status).body(response);
    }
}

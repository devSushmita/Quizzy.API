package com.innobyteservices.quizzy.api.dto.response;

import lombok.Data;

/**
 * Standard structure for error responses returned by the API.
 */
@Data
public class ErrorResponse {

    /**
     * Application-specific error code.
     */
    private int code;

    /**
     * Descriptive error message.
     */
    private String message;
}

package com.univision.assessmentwebapi.exception;

import com.univision.assessmentwebapi.model.exception.APIError;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;

/**
 * Let throw custom errors
 */
public class APIThrowable extends NestedRuntimeException {

    private final APIError errorMessage;

    public APIThrowable(String message, Throwable error) {
        super(message, error);
        this.errorMessage = new APIError(HttpStatus.INTERNAL_SERVER_ERROR, message, error);
    }

    public APIThrowable(HttpStatus status, String message, Throwable error) {
        super(message, error);
        this.errorMessage = new APIError(status, message, error);
    }

    public APIThrowable(HttpStatus status, String message) {
        super(message);
        this.errorMessage = new APIError(status, message);
    }

    public APIError getErrorMessage() {
        return this.errorMessage;
    }

}

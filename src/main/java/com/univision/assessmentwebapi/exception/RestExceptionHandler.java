package com.univision.assessmentwebapi.exception;

import com.univision.assessmentwebapi.model.exception.APIError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

/**
 * Intercept all the exceptions in the API
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIError> exception (Exception ex) {
        logger.error("Unexpected ERROR ", ex);

        // Handle error when there are missing parameters
        if (ex instanceof MissingServletRequestParameterException) {
            return new ResponseEntity<>(new APIError(HttpStatus.BAD_REQUEST, ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Default handler Exception
        return new ResponseEntity<>(new APIError(HttpStatus.INTERNAL_SERVER_ERROR, ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Custom throwable object to let the developer throw custom errors
    @ExceptionHandler(APIThrowable.class)
    public ResponseEntity<APIError> exception (APIThrowable ex) {

        if (Objects.nonNull(ex) && Objects.nonNull(ex.getErrorMessage())) {
            logger.error("Error threw: {}" , ex.getErrorMessage().toString());
        }

        return new ResponseEntity<>(ex.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

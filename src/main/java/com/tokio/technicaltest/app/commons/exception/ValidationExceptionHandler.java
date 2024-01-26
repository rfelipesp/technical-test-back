package com.tokio.technicaltest.app.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Map<String, String>>> handleValidationException(MethodArgumentNotValidException exception) {

        List<String> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage).toList();

        Map<String, Map<String, String>> errorsResponse = new HashMap<>();
        errorsResponse.put("errors", buildErrorResponse(errors));

        return new ResponseEntity<>(errorsResponse, HttpStatus.BAD_REQUEST);
    }

    private Map<String, String> buildErrorResponse(List<String> errors) {

        Map<String, String> errorResponse = new HashMap<>();

        errors.forEach(it -> {
            var indexOf = it.indexOf("|");
            var fieldError = it.substring(0, indexOf - 1);
            var fieldMessage = it.substring(indexOf + 2);
            errorResponse.put(fieldError, fieldMessage);
        });

        return errorResponse;
    }

}

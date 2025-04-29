package com.birnbickl.Touren_Zeit_Erfassung.ErrorHandling;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiError> handleValidationException (MethodArgumentNotValidException exception) {
        List<String> errors;

        errors = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        ApiError apiError = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Validation failed.", errors);
        return ResponseEntity.badRequest().body(apiError);
    }


    @ExceptionHandler
    public ResponseEntity<ApiError> handleNotFound (NoSuchElementException exception) {
        List<String> errors = List.of("The requested resource was not found.");
        ApiError apiError = new ApiError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Not found.", errors);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }


    @ExceptionHandler
    public ResponseEntity<ApiError> handleConstraintViolation (ConstraintViolationException exception) {
        List<String> errors = exception.getConstraintViolations()
                .stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .toList();

        ApiError apiError = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Constraint violation.", errors);
        return ResponseEntity.badRequest().body(apiError);

    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleEntityNotFoundException (EntityNotFoundException exception) {
        List<String> errors = List.of("The requested entity was not found.");
        ApiError apiError = new ApiError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Not found.", errors);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleGenericException (Exception exception) {
        List<String> errors = List.of("An unknown error occurred.");
        ApiError apiError = new ApiError(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error.", errors);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> usernameAlreadyExists(UsernameAlreadyExistsException exception) {
        List<String> errors = List.of(exception.getMessage());
        ApiError apiError = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Username already exists.", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

}

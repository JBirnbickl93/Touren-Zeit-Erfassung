package com.birnbickl.Touren_Zeit_Erfassung.ErrorHandling;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}

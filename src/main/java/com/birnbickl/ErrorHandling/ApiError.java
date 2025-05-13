package com.birnbickl.Touren_Zeit_Erfassung.ErrorHandling;

import java.time.LocalDateTime;
import java.util.List;

// Klasse um die Schnittstellen-Fehler aufzufangen.
// Weitere Fehler / Ausnahmen werden in entsprechenden Klassen implementiert.

public class ApiError {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private List<String> details;


    public ApiError(LocalDateTime timestamp, int status, String message, List<String> details) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.details = details;
    }

    public List<String> getDetails() {
        return details;
    }
    public void setDetails(List<String> details) {
        this.details = details;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }



}

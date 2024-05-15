package com.RestApiTemplate.RestApiTemplate.ErrorHandling;

import java.time.LocalDateTime;

public class ErrorDetails {
    private String message;
    private String details;
    private LocalDateTime date ;

    public ErrorDetails(String message, String details, LocalDateTime date) {
        this.message = message;
        this.details = details;
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}

package com.katrasolutions.feedbackservice.exception;

public class ExceptionModel {

    private final String message;

    public ExceptionModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

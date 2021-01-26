package com.katrasolutions.feedbackservice.exception;

public class RestApiNotFoundException extends RuntimeException {

    private final String message;

    public RestApiNotFoundException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
        this.message = exceptionMessage.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}

package com.katrasolutions.feedbackservice.exception;

public class RestApiForbiddenException extends RuntimeException {

    private final String message;

    public RestApiForbiddenException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
        this.message = exceptionMessage.getMessage();

    }

    @Override
    public String getMessage() {
        return message;
    }

}

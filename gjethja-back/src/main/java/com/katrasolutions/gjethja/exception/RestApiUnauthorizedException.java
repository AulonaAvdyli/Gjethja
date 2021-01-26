package com.katrasolutions.gjethja.exception;

public class RestApiUnauthorizedException extends RuntimeException {


    private final String message;

    public RestApiUnauthorizedException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
        this.message = exceptionMessage.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}

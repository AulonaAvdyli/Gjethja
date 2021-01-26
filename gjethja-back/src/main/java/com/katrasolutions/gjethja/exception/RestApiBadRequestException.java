package com.katrasolutions.gjethja.exception;

public class RestApiBadRequestException extends RuntimeException {
    private final String message;

    public RestApiBadRequestException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
        this.message = exceptionMessage.getMessage();

    }

    @Override
    public String getMessage() {
        return message;
    }

}

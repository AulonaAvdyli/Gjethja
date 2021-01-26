package com.katrasolutions.gjethja.exception;

public class RestApiUnsupportedMediaFileException extends RuntimeException {


    private final String message;

    public RestApiUnsupportedMediaFileException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
        this.message = exceptionMessage.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}

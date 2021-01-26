package com.katrasolutions.gjethja.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ObjectError objectError = ex.getBindingResult().getAllErrors().get(0);
        String defaultMessage = objectError.getDefaultMessage();
        errors.put("message", defaultMessage);
        return errors;
    }

    @ExceptionHandler(RestApiNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionModel handleNotFoundException(RestApiNotFoundException ex) {
        return new ExceptionModel(ex.getMessage());
    }

    @ExceptionHandler(RestApiUnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionModel handleUnauthorizedException(RestApiUnauthorizedException ex) {
        return new ExceptionModel(ex.getMessage());
    }

    @ExceptionHandler(RestApiUnsupportedMediaFileException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ExceptionModel handleUnauthorizedException(RestApiUnsupportedMediaFileException ex) {
        return new ExceptionModel(ex.getMessage());
    }

    @ExceptionHandler(RestApiForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionModel handleForbiddenException(RestApiForbiddenException exception) {
        return new ExceptionModel(exception.getMessage());

    }

    @ExceptionHandler(RestApiBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionModel handleBadRequestException(RestApiBadRequestException exception) {
        return new ExceptionModel(exception.getMessage());
    }
}

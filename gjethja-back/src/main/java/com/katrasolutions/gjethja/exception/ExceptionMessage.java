package com.katrasolutions.gjethja.exception;

public enum ExceptionMessage {

    USER_NOT_FOUND("User not found"),
    JOB_NOT_FOUND("Job doesn't exist"),
    TOKEN_UNAUTHORIZED("The token has expired"),
    FORMAT_NOT_SUPPORTED("That format is not acceptable"),
    UNAUTHORIZED_VALIDATION("You have no access to this, please log in"),
    POST_NOT_FOUND("Post not found"),
    UPDATE_POST_FORBIDDEN("Can't update others posts"),
    DELETE_POST_FORBIDDEN("Cant't delete others posts"),
    EMAIL_NOT_FOUND("Email is invalid"),
    ADD_CERTIFICATE_FORBIDDEN("Seeker can't upload certificate"),
    AGE_BAD_REQUEST("EndAge shouldn't be less than startAge"),
    CERTIFICATE_NOT_FOUND("Certificate not found"),
    PASSWORD_BAD_REQUEST("Current password doesn't match!");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

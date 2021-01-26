package com.katrasolutions.feedbackservice.exception;

public enum ExceptionMessage {

    FEEDBACK_NOT_FOUND("Feedback not found"),
    UPDATE_FEEDBACK_FORBIDDEN("Can't update others feedbacks");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

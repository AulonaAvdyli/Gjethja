package com.katrasolutions.gjethja.response;

public class RegisterResponse {

    private Boolean isRegistered;
    private String message;

    public Boolean getRegistered() {
        return isRegistered;
    }

    public void setRegistered(Boolean registered) {
        isRegistered = registered;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RegisterResponse(Boolean isRegistered, String message) {
        this.isRegistered = isRegistered;
        this.message = message;
    }
}

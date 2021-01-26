package com.katrasolutions.gjethja.request;

import javax.validation.constraints.NotEmpty;

public class ForgotPasswordRequest {

    @NotEmpty(message = "email must not be empty")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

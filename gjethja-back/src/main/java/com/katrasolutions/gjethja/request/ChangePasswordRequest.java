package com.katrasolutions.gjethja.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ChangePasswordRequest {

    @NotEmpty(message = "password must not be empty")
    private String oldPassword;

    @Size(min = 4, message = "new password must be at least 4 characters long")
    @NotBlank(message = "new password must not be empty")
    private String newPassword;

    @NotBlank(message = "confirm new password must not be empty")
    private String confirmNewPassword;

    @JsonIgnore
    @AssertTrue(message = "passwords do not match")
    public boolean isNewPasswordEqualToConfirmNewPassword() {
        return newPassword.equals(confirmNewPassword);
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}

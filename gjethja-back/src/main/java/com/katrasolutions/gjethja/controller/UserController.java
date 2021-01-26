package com.katrasolutions.gjethja.controller;

import com.katrasolutions.gjethja.request.*;
import com.katrasolutions.gjethja.response.LoginResponse;
import com.katrasolutions.gjethja.response.UserResponse;
import com.katrasolutions.gjethja.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @GetMapping("/confirm-account")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmAccount(@RequestParam("token") String token) {
        userService.confirmAccount(token);
    }

    @PostMapping("/profile-picture")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addProfilePicture(@RequestParam("profile-picture") MultipartFile multipartFile) {
        userService.addProfilePicture(multipartFile);
    }

    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest, HttpServletRequest httpServletRequest) {
        userService.forgotPassword(forgotPasswordRequest, httpServletRequest);
    }

    @PutMapping("/reset-password")
    public void resetPassword(@RequestBody @Valid ResetPasswordRequest passwordRequest, @RequestParam("key") String key) {
        userService.resetPassword(passwordRequest, key);
    }

    @GetMapping("/current")
    public UserResponse getCurrentUser() {
        return userService.getCurrentUser();
    }

    @PatchMapping("/change-password")
    public void changePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        userService.changePassword(changePasswordRequest);
    }

    @PutMapping("/edit-profile")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(request);
    }
}

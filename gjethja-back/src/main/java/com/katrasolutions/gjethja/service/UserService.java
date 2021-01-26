package com.katrasolutions.gjethja.service;

import com.katrasolutions.gjethja.request.*;
import com.katrasolutions.gjethja.response.LoginResponse;
import com.katrasolutions.gjethja.response.UserResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    LoginResponse login(LoginRequest loginRequest);

    void confirmAccount(String token);

    void addProfilePicture(MultipartFile multipartFile);

    void forgotPassword(ForgotPasswordRequest forgotPasswordRequest, HttpServletRequest httpServletRequest);

    void resetPassword(ResetPasswordRequest resetPasswordRequest, String token);

    UserResponse getCurrentUser();

    void changePassword(ChangePasswordRequest changePasswordRequest);

    void updateUser(UserUpdateRequest request);
}

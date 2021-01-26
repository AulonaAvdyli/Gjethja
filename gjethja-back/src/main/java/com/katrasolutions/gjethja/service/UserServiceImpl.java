package com.katrasolutions.gjethja.service;

import com.katrasolutions.gjethja.entity.ConfirmationToken;
import com.katrasolutions.gjethja.entity.PasswordResetToken;
import com.katrasolutions.gjethja.entity.User;
import com.katrasolutions.gjethja.exception.ExceptionMessage;
import com.katrasolutions.gjethja.exception.RestApiBadRequestException;
import com.katrasolutions.gjethja.exception.RestApiNotFoundException;
import com.katrasolutions.gjethja.exception.RestApiUnauthorizedException;
import com.katrasolutions.gjethja.mapper.LoginMapper;
import com.katrasolutions.gjethja.mapper.UserMapper;
import com.katrasolutions.gjethja.repository.ConfirmationTokenRepository;
import com.katrasolutions.gjethja.repository.PasswordResetTokenRepository;
import com.katrasolutions.gjethja.repository.UserRepository;
import com.katrasolutions.gjethja.request.*;
import com.katrasolutions.gjethja.response.LoginResponse;
import com.katrasolutions.gjethja.response.UserResponse;
import com.katrasolutions.gjethja.util.ImageUtils;
import com.katrasolutions.gjethja.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private LoginMapper loginMapper;
    private ConfirmationTokenRepository confirmationTokenRepository;
    private EmailSenderService emailSenderService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    public UserServiceImpl(UserRepository userRepository, LoginMapper loginMapper, ConfirmationTokenRepository confirmationTokenRepository, EmailSenderService emailSenderService, PasswordEncoder passwordEncoder, PasswordResetTokenRepository passwordResetTokenRepository) {
        this.userRepository = userRepository;
        this.loginMapper = loginMapper;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailSenderService = emailSenderService;
        this.passwordEncoder = passwordEncoder;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    @Transactional
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return loginMapper.fromRequestToResponse(loginRequest);
    }

    @Transactional
    @Override
    public void confirmAccount(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token);
        if (confirmationToken != null) {
            User provider = userRepository.findByEmail(confirmationToken.getUser().getEmail());
            provider.setEnabled(true);
            userRepository.save(provider);
            confirmationTokenRepository.delete(confirmationToken);
        }
    }

    @Override
    @Transactional
    public void addProfilePicture(MultipartFile multipartFile) {
        String currentUser = SecurityUtils.validateCurrentUser().orElseThrow(() -> new RestApiUnauthorizedException(ExceptionMessage.UNAUTHORIZED_VALIDATION));
        User user = Optional.ofNullable(userRepository.findByEmail(currentUser)).orElseThrow(() -> new RestApiNotFoundException(ExceptionMessage.USER_NOT_FOUND));
        ImageUtils.validateImageContent(multipartFile.getContentType());
        try {
            user.setProfilePicture(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        userRepository.save(user);
    }

    @Override
    public void forgotPassword(ForgotPasswordRequest forgotPasswordRequest, HttpServletRequest httpServletRequest) {
        User user = Optional.ofNullable(userRepository.findByEmail(forgotPasswordRequest.getEmail()))
                .orElseThrow(() -> new RestApiNotFoundException(ExceptionMessage.USER_NOT_FOUND));
        emailSenderService.sendPasswordResetEmail(user, resetPasswordUrl(httpServletRequest));
    }

    @Override
    public void resetPassword(ResetPasswordRequest resetPasswordRequest, String token) {
        PasswordResetToken passwordResetToken = Optional.ofNullable(passwordResetTokenRepository.findByToken(token))
                .orElseThrow(() -> new RestApiUnauthorizedException(ExceptionMessage.TOKEN_UNAUTHORIZED));
        User user = userRepository.findByEmail(passwordResetToken.getUser().getEmail());
        user.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
        userRepository.saveAndFlush(user);
    }

    @Override
    public UserResponse getCurrentUser() {
        String currentUser = SecurityUtils.validateCurrentUser().orElseThrow(() -> new RestApiUnauthorizedException(ExceptionMessage.UNAUTHORIZED_VALIDATION));
        User user = Optional.ofNullable(userRepository.findByEmail(currentUser)).orElseThrow(() -> new RestApiNotFoundException(ExceptionMessage.USER_NOT_FOUND));
        return userMapper.userToModel(user);
    }

    private String resetPasswordUrl(HttpServletRequest httpServletRequest) {
        String requestUrl = httpServletRequest.getRequestURL().toString();
        return requestUrl.replaceAll("forgot-password", "reset-password");
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        String currentUser = SecurityUtils.validateCurrentUser().orElseThrow(() -> new RestApiUnauthorizedException(ExceptionMessage.UNAUTHORIZED_VALIDATION));
        User user = userRepository.findByEmail(currentUser);
        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new RestApiBadRequestException(ExceptionMessage.PASSWORD_BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(UserUpdateRequest request) {
        String currentUser = SecurityUtils.validateCurrentUser().orElseThrow(() -> new RestApiUnauthorizedException(ExceptionMessage.UNAUTHORIZED_VALIDATION));
        User user = userRepository.findByEmail(currentUser);
        updateExistingEntity(user, request);
    }

    private void updateExistingEntity(User entity, UserUpdateRequest request) {
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setBio(request.getBio());
        entity.setAddress(request.getAddress());
        entity.setCity(request.getCity());
        entity.setPhoneNumber(request.getPhoneNumber());
        entity.setAddress(request.getAddress());
    }
}

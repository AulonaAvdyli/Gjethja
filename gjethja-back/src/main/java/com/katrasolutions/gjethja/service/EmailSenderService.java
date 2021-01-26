package com.katrasolutions.gjethja.service;

import com.katrasolutions.gjethja.entity.User;

public interface EmailSenderService {

    void sendConfirmationEmail(User user, String requestUrl);

    void sendPasswordResetEmail(User user, String requestUrl);
}

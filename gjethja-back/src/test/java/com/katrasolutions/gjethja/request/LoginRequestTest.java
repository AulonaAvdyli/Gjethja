package com.katrasolutions.gjethja.request;

import com.katrasolutions.gjethja.request.LoginRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class LoginRequestTest {

    private static Validator validator;

    private static ValidatorFactory validatorFactory;

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void loginRequestWithEmptyEmail() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("");
        loginRequest.setPassword("password");
        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);
        ConstraintViolation<LoginRequest> violation = violations.iterator().next();

        assertEquals("email must not be empty", violation.getMessage());
    }

    @Test
    public void loginRequestWithEmptyPassword() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("email@example.com");
        loginRequest.setPassword("");
        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);
        ConstraintViolation<LoginRequest> violation = violations.iterator().next();

        assertEquals("password must not be empty", violation.getMessage());
    }
}

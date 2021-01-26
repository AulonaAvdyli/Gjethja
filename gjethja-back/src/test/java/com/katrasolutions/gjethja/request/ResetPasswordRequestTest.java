package com.katrasolutions.gjethja.request;

import com.katrasolutions.gjethja.request.ResetPasswordRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResetPasswordRequestTest {

    private static Validator validator;

    private static ValidatorFactory validatorFactory;

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void resetPasswordRequestWithEmptyNewPassword() {
        ResetPasswordRequest resetPasswordRequest = defaultResetPasswordRequest();
        resetPasswordRequest.setNewPassword("     ");
        resetPasswordRequest.setConfirmNewPassword("     ");
        Set<ConstraintViolation<ResetPasswordRequest>> validate = validator.validate(resetPasswordRequest);
        ConstraintViolation<ResetPasswordRequest> violation = validate.iterator().next();
        assertTrue(validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()).contains("new password must not be empty"));

    }

    @Test
    public void resetPasswordRequestWithEmptyConfirmNewPassword() {
        ResetPasswordRequest resetPasswordRequest = defaultResetPasswordRequest();
        resetPasswordRequest.setConfirmNewPassword("     ");
        resetPasswordRequest.setNewPassword("     ");
        Set<ConstraintViolation<ResetPasswordRequest>> validate = validator.validate(resetPasswordRequest);
        assertTrue(validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()).contains("confirm new password must not be empty"));

    }

    @Test
    public void resetPasswordRequestWithDifferentConfirmNewPassword() {
        ResetPasswordRequest resetPasswordRequest = defaultResetPasswordRequest();
        resetPasswordRequest.setConfirmNewPassword("omg");
        Set<ConstraintViolation<ResetPasswordRequest>> validate = validator.validate(resetPasswordRequest);
        ConstraintViolation<ResetPasswordRequest> violation = validate.iterator().next();
        assertEquals("passwords do not match", violation.getMessage());
    }

    private static ResetPasswordRequest defaultResetPasswordRequest() {
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setNewPassword("password");
        resetPasswordRequest.setConfirmNewPassword("password");
        return resetPasswordRequest;
    }
}

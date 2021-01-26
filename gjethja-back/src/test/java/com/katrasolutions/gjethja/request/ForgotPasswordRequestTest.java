package com.katrasolutions.gjethja.request;

import com.katrasolutions.gjethja.request.ForgotPasswordRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ForgotPasswordRequestTest {

    private static Validator validator;

    private static ValidatorFactory validatorFactory;

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void forgotPasswordRequestWithEmptyEmailTest() {
        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
        forgotPasswordRequest.setEmail("");
        Set<ConstraintViolation<ForgotPasswordRequest>> validate = validator.validate(forgotPasswordRequest);
        ConstraintViolation<ForgotPasswordRequest> violation = validate.iterator().next();
        assertEquals("email must not be empty", violation.getMessage());
    }
}

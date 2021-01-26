package com.katrasolutions.gjethja.request;

import com.katrasolutions.gjethja.request.RegisterRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterRequestTest {

    private static Validator validator;

    private static ValidatorFactory validatorFactory;

    private static final String FIRST_NAME = "first";
    private static final String LAST_NAME = "last";
    private static final String EMAIL = "email@example.com";
    private static final String PASSWORD = "password";
    private static final String CONFIRM_PASSWORD = "password";
    private static final String BIO = "bio";
    private static final LocalDate DATE_OF_BIRTH = LocalDate.now().minusYears(20);
    private static final String CITY = "city";
    private static final String ADDRESS = "address";
    private static final String GENDER = "gender";
    private static final String PHONE_NUMBER = "phone";

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void registerRequestWithAgeBelow18() {
        RegisterRequest registerRequest = getDefaultBuilder().setDateOfBirth(LocalDate.now().minusYears(10)).build();
        Set<ConstraintViolation<RegisterRequest>> validate = validator.validate(registerRequest);
        ConstraintViolation<RegisterRequest> violation = validate.iterator().next();
        assertEquals("age must be 18+", violation.getMessage());
    }

    @Test
    public void registerRequestWithWrongEmailFormat() {
        RegisterRequest registerRequest = getDefaultBuilder().setEmail("wrongFormat").build();
        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(registerRequest);
        ConstraintViolation<RegisterRequest> violation = violations.iterator().next();
        assertEquals("Please, enter a valid email", violation.getMessage());
    }

    @Test
    public void registerRequestWithEmptyFirstName() {
        RegisterRequest registerRequest = getDefaultBuilder().setFirstName("").build();
        Set<ConstraintViolation<RegisterRequest>> validate = validator.validate(registerRequest);
        ConstraintViolation<RegisterRequest> violation = validate.iterator().next();
        assertEquals("first name must not be empty", violation.getMessage());
    }

    @Test
    public void registerRequestWithEmptyLastName() {
        RegisterRequest registerRequest = getDefaultBuilder().setLastName("").build();
        Set<ConstraintViolation<RegisterRequest>> validate = validator.validate(registerRequest);
        ConstraintViolation<RegisterRequest> violation = validate.iterator().next();
        assertEquals("last name must not be empty", violation.getMessage());
    }

    @Test
    public void registerRequestWithEmptyPassword() {
        RegisterRequest registerRequest = getDefaultBuilder().setPassword("").build();
        Set<ConstraintViolation<RegisterRequest>> validate = validator.validate(registerRequest);
        assertTrue(validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()).contains("password must not be empty"));
    }

    @Test
    public void registerRequestWithTrueDifferentConfirmPassword() {
        RegisterRequest registerRequest = getDefaultBuilder().setConfirmPassword("hjskjsj").build();
        Set<ConstraintViolation<RegisterRequest>> validate = validator.validate(registerRequest);
        ConstraintViolation<RegisterRequest> violation = validate.iterator().next();
        assertEquals("Passwords do not match", violation.getMessage());
    }

    @Test
    public void registerRequestWithEmptyCity() {
        RegisterRequest registerRequest = getDefaultBuilder().setCity("").build();
        Set<ConstraintViolation<RegisterRequest>> validate = validator.validate(registerRequest);
        ConstraintViolation<RegisterRequest> violation = validate.iterator().next();
        assertEquals("city must not be empty", violation.getMessage());
    }

    @Test
    public void registerRequestWithEmptyAddress() {
        RegisterRequest registerRequest = getDefaultBuilder().setAddress("").build();
        Set<ConstraintViolation<RegisterRequest>> validate = validator.validate(registerRequest);
        ConstraintViolation<RegisterRequest> violation = validate.iterator().next();
        assertEquals("Address must not be empty", violation.getMessage());
    }

    @Test
    public void registerRequestWithEmptyGender() {
        RegisterRequest registerRequest = getDefaultBuilder().setGender("").build();
        Set<ConstraintViolation<RegisterRequest>> validate = validator.validate(registerRequest);
        ConstraintViolation<RegisterRequest> violation = validate.iterator().next();
        assertEquals("gender must not be empty", violation.getMessage());
    }

    @Test
    public void registerRequestWithEmptyPhoneNumber() {
        RegisterRequest registerRequest = getDefaultBuilder().setPhoneNumber("").build();
        Set<ConstraintViolation<RegisterRequest>> validate = validator.validate(registerRequest);
        ConstraintViolation<RegisterRequest> violation = validate.iterator().next();
        assertEquals("phone number must not be empty", violation.getMessage());
    }

    private static RegisterRequest.Builder getDefaultBuilder() {
        return new RegisterRequest.Builder().setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setEmail(EMAIL)
                .setPassword(PASSWORD)
                .setConfirmPassword(CONFIRM_PASSWORD)
                .setBio(BIO)
                .setDateOfBirth(DATE_OF_BIRTH)
                .setCity(CITY)
                .setAddress(ADDRESS)
                .setGender(GENDER)
                .setPhoneNumber(PHONE_NUMBER);
    }
}

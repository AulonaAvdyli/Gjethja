package com.katrasolutions.gjethja.request;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ProviderRegisterRequestTest {

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
    private static final String EDUCATION = "education";
    private static final List<String> JOBS = Collections.singletonList("eldercare");

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void providerRegisterRequestWithEmptyJobs() {
        ProviderRegisterRequest providerRegisterRequest = getProviderRegisterRequest();
        providerRegisterRequest.setJobs(null);
        Set<ConstraintViolation<ProviderRegisterRequest>> validate = validator.validate(providerRegisterRequest);
        ConstraintViolation<ProviderRegisterRequest> violation = validate.iterator().next();
        assertEquals("Jobs must not be null", violation.getMessage());
    }

    private static ProviderRegisterRequest getProviderRegisterRequest() {
        ProviderRegisterRequest providerRegisterRequest = new ProviderRegisterRequest();
        providerRegisterRequest.setFirstName(FIRST_NAME);
        providerRegisterRequest.setLastName(LAST_NAME);
        providerRegisterRequest.setAddress(ADDRESS);
        providerRegisterRequest.setBio(BIO);
        providerRegisterRequest.setCity(CITY);
        providerRegisterRequest.setPassword(PASSWORD);
        providerRegisterRequest.setConfirmPassword(CONFIRM_PASSWORD);
        providerRegisterRequest.setJobs(JOBS);
        providerRegisterRequest.setDateOfBirth(DATE_OF_BIRTH);
        providerRegisterRequest.setGender(GENDER);
        providerRegisterRequest.setEmail(EMAIL);
        providerRegisterRequest.setPhoneNumber(PHONE_NUMBER);
        providerRegisterRequest.setEducation(EDUCATION);
        return providerRegisterRequest;
    }

}

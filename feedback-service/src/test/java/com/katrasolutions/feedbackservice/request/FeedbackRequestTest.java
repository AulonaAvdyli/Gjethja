package com.katrasolutions.feedbackservice.request;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeedbackRequestTest {

    private static Validator validator;

    private static ValidatorFactory validatorFactory;

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "test";
    private static final Date CREATED_ON = new Date();

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void feedbackRequestWithEmptyDescription() {
        FeedbackRequest feedbackRequest = getFeedbackRequest();
        feedbackRequest.setDescription(null);
        Set<ConstraintViolation<FeedbackRequest>> validate = validator.validate(feedbackRequest);
        ConstraintViolation<FeedbackRequest> violation = validate.iterator().next();
        assertEquals("description must not be empty", violation.getMessage());
    }

    private static FeedbackRequest getFeedbackRequest() {
        FeedbackRequest feedbackRequest = new FeedbackRequest();
        feedbackRequest.setId(ID);
        feedbackRequest.setCreatedOn(CREATED_ON);
        feedbackRequest.setDescription(DESCRIPTION);
        return feedbackRequest;
    }
}

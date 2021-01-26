package com.katrasolutions.feedbackservice.mapper;

import com.katrasolutions.feedbackservice.entity.Feedback;
import com.katrasolutions.feedbackservice.request.FeedbackRequest;
import com.katrasolutions.feedbackservice.request.FeedbackUpdateRequest;
import com.katrasolutions.feedbackservice.response.FeedbackResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeedbackMapperTest {
    private static final Long ID = 1L;
    private static final String DESCRIPTION = "description";
    private static final String UPDATEDESCRIPTION = "update description";
    private static final Date CREATED_ON = new Date();
    private static final String FIRSTNAME = "First Name";
    private static final String LASTNAME = "Last Name";
    private static final String EMAIL = "test@test.com";

    @InjectMocks
    private FeedbackMapper feedbackMapper = new FeedbackMapper();

    @Test
    public void toEntity() {
        FeedbackRequest feedbackRequest = getFeedbackRequest();
        Feedback feedback = feedbackMapper.toEntity(feedbackRequest);
        feedback.setFirstName(FIRSTNAME);
        feedback.setLastName(LASTNAME);
        feedback.setEmail(EMAIL);
        assertEquals(feedback.getDescription(), DESCRIPTION);
        assertEquals(feedback.getCreatedOn().toString(), CREATED_ON.toString());
        assertEquals(feedback.getFirstName(), FIRSTNAME);
        assertEquals(feedback.getLastName(), LASTNAME);
        assertEquals(feedback.getEmail(), EMAIL);
    }

    @Test
    public void toResponse() {
        Feedback feedback = getFeedback();
        FeedbackResponse feedbackResponse = feedbackMapper.toResponse(feedback);
        assertEquals(feedbackResponse.getId(), ID);
        assertEquals(feedbackResponse.getDescription(), DESCRIPTION);
        assertEquals(feedbackResponse.getCreatedOn().toString(), CREATED_ON.toString());
        assertEquals(feedbackResponse.getFirstName(), FIRSTNAME);
        assertEquals(feedbackResponse.getLastName(), LASTNAME);
        assertEquals(feedbackResponse.getEmail(), EMAIL);
    }

    @Test
    public void updateToEntity() {
        FeedbackUpdateRequest feedbackUpdateRequest = getFeedbackUpdateRequest();
        Feedback feedback = getFeedback();
        Feedback updatedFeedback = feedbackMapper.updateToEntity(feedback, feedbackUpdateRequest);
        assertEquals(updatedFeedback.getId(), ID);
        assertEquals(updatedFeedback.getDescription(), UPDATEDESCRIPTION);
        assertEquals(updatedFeedback.getCreatedOn().toString(), CREATED_ON.toString());
        assertEquals(updatedFeedback.getFirstName(), FIRSTNAME);
        assertEquals(updatedFeedback.getLastName(), LASTNAME);
        assertEquals(updatedFeedback.getEmail(), EMAIL);
    }

    private static FeedbackRequest getFeedbackRequest() {
        FeedbackRequest feedbackRequest = new FeedbackRequest();
        feedbackRequest.setDescription(DESCRIPTION);
        feedbackRequest.setCreatedOn(CREATED_ON);
        return feedbackRequest;
    }

    private static FeedbackUpdateRequest getFeedbackUpdateRequest() {
        FeedbackUpdateRequest feedbackUpdateRequest = new FeedbackUpdateRequest();
        feedbackUpdateRequest.setDescription(UPDATEDESCRIPTION);
        return feedbackUpdateRequest;
    }

    private static Feedback getFeedback() {
        Feedback feedback = new Feedback();
        feedback.setId(ID);
        feedback.setDescription(DESCRIPTION);
        feedback.setCreatedOn(CREATED_ON);
        feedback.setFirstName(FIRSTNAME);
        feedback.setLastName(LASTNAME);
        feedback.setEmail(EMAIL);
        return feedback;
    }
}


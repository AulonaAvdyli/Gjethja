package com.katrasolutions.feedbackservice.mapper;

import com.katrasolutions.feedbackservice.entity.Feedback;
import com.katrasolutions.feedbackservice.request.FeedbackRequest;
import com.katrasolutions.feedbackservice.request.FeedbackUpdateRequest;
import com.katrasolutions.feedbackservice.response.FeedbackResponse;
import com.katrasolutions.feedbackservice.util.ImageUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FeedbackMapper {

    public Feedback toEntity(FeedbackRequest feedbackRequest) {
        Feedback feedback = new Feedback();
        feedback.setDescription(feedbackRequest.getDescription());
        feedback.setId(feedbackRequest.getId());
        feedback.setCreatedOn(new Date());
        return feedback;
    }

    public FeedbackResponse toResponse(Feedback feedback) {
        FeedbackResponse feedbackResponse = new FeedbackResponse();
        feedbackResponse.setCreatedOn(feedback.getCreatedOn());
        feedbackResponse.setDescription(feedback.getDescription());
        feedbackResponse.setId(feedback.getId());
        feedbackResponse.setFirstName(feedback.getFirstName());
        feedbackResponse.setLastName(feedback.getLastName());
        feedbackResponse.setEmail(feedback.getEmail());
        feedbackResponse.setProfilePicture(ImageUtils.convertImageToString(feedback.getProfilePicture()));
        return feedbackResponse;
    }

    public Feedback updateToEntity(Feedback feedback, FeedbackUpdateRequest feedbackUpdateRequest) {
        if (feedbackUpdateRequest.getDescription() != null) {
            feedback.setDescription(feedbackUpdateRequest.getDescription());
        }
        return feedback;
    }

}

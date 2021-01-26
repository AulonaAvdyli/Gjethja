package com.katrasolutions.feedbackservice.service;

import com.katrasolutions.feedbackservice.request.FeedbackRequest;
import com.katrasolutions.feedbackservice.request.FeedbackUpdateRequest;
import com.katrasolutions.feedbackservice.response.FeedbackResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface FeedbackService {

    FeedbackResponse getById(Long id);

    List<FeedbackResponse> getAll();

    FeedbackResponse create(FeedbackRequest feedbackRequest, HttpServletRequest httpServletRequest);

    FeedbackResponse update(Long id, FeedbackUpdateRequest feedbackUpdateRequest, HttpServletRequest httpServletRequest);

    void delete(Long id);
}

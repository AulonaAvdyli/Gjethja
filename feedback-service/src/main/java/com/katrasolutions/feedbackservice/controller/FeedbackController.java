package com.katrasolutions.feedbackservice.controller;

import com.katrasolutions.feedbackservice.request.FeedbackRequest;
import com.katrasolutions.feedbackservice.request.FeedbackUpdateRequest;
import com.katrasolutions.feedbackservice.response.FeedbackResponse;
import com.katrasolutions.feedbackservice.service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value ="/feedback", produces = MediaType.APPLICATION_JSON_VALUE)
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/{id}")
    public FeedbackResponse findById(@PathVariable Long id) {
        return feedbackService.getById(id);
    }

    @GetMapping
    public List<FeedbackResponse> findAll() {
        return feedbackService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FeedbackResponse create(@RequestBody @Valid FeedbackRequest feedbackRequest, HttpServletRequest httpServletRequest) {
        return feedbackService.create(feedbackRequest, httpServletRequest);
    }

    @PutMapping("/{id}")
    public FeedbackResponse update(@PathVariable Long id, @RequestBody @Valid FeedbackUpdateRequest feedbackUpdateRequest, HttpServletRequest httpServletRequest) {
        return feedbackService.update(id, feedbackUpdateRequest, httpServletRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        feedbackService.delete(id);
    }
}

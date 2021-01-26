package com.katrasolutions.feedbackservice.service;

import com.katrasolutions.feedbackservice.entity.Feedback;
import com.katrasolutions.feedbackservice.exception.ExceptionMessage;
import com.katrasolutions.feedbackservice.exception.RestApiForbiddenException;
import com.katrasolutions.feedbackservice.exception.RestApiNotFoundException;
import com.katrasolutions.feedbackservice.mapper.FeedbackMapper;
import com.katrasolutions.feedbackservice.model.UserModel;
import com.katrasolutions.feedbackservice.repository.FeedbackRepository;
import com.katrasolutions.feedbackservice.request.FeedbackRequest;
import com.katrasolutions.feedbackservice.request.FeedbackUpdateRequest;
import com.katrasolutions.feedbackservice.response.FeedbackResponse;
import com.katrasolutions.feedbackservice.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public FeedbackResponse getById(Long id) {
        Feedback feedback = feedbackRepository.findById(id).orElseThrow(() -> new RestApiNotFoundException(ExceptionMessage.FEEDBACK_NOT_FOUND));
        return feedbackMapper.toResponse(feedback);
    }

    @Override
    public List<FeedbackResponse> getAll() {
        return feedbackRepository.findAll().stream().map(feedbackMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FeedbackResponse create(FeedbackRequest feedbackRequest, HttpServletRequest httpServletRequest) {
        Feedback feedback = feedbackMapper.toEntity(feedbackRequest);
        UserModel currentUser = getCurrentUser(httpServletRequest);
        feedback.setFirstName(currentUser.getFirstName());
        feedback.setLastName(currentUser.getLastName());
        feedback.setEmail(currentUser.getEmail());
        feedback.setProfilePicture(ImageUtils.convertStringToImage(currentUser.getProfilePicture()));
        feedbackRepository.save(feedback);
        return feedbackMapper.toResponse(feedback);
    }

    @Override
    @Transactional
    public FeedbackResponse update(Long id, FeedbackUpdateRequest feedbackUpdateRequest, HttpServletRequest httpServletRequest) {
        Feedback feedback = feedbackRepository.findById(id).orElseThrow(() -> new RestApiNotFoundException(ExceptionMessage.FEEDBACK_NOT_FOUND));
        UserModel currentUser = getCurrentUser(httpServletRequest);
        if (!currentUser.getEmail().equals(feedback.getEmail())){
            throw new RestApiForbiddenException(ExceptionMessage.UPDATE_FEEDBACK_FORBIDDEN);
        }
        feedback = feedbackMapper.updateToEntity(feedback, feedbackUpdateRequest);
        feedbackRepository.save(feedback);
        return feedbackMapper.toResponse(feedback);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Feedback post = feedbackRepository.findById(id).orElseThrow(() -> new RestApiNotFoundException(ExceptionMessage.FEEDBACK_NOT_FOUND));
        feedbackRepository.delete(post);
    }

    private UserModel getCurrentUser(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorization);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<UserModel> restExchange =
                restTemplate.exchange("http://main-leaf-service/api/current",
                        HttpMethod.GET,
                        httpEntity, new ParameterizedTypeReference<UserModel>() {
                        });
        return restExchange.getBody();
    }
}

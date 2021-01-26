package com.katrasolutions.paymentservice.service;

import com.katrasolutions.paymentservice.model.PostsModel;
import com.katrasolutions.paymentservice.request.PaymentRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PaymentService {
    PostsModel boostPost(Long id, PaymentRequest request, HttpServletRequest servletRequest) throws StripeException;

    List<PostsModel> findAllPosts();
}

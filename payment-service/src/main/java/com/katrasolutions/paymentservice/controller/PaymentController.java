package com.katrasolutions.paymentservice.controller;

import com.katrasolutions.paymentservice.model.PostsModel;
import com.katrasolutions.paymentservice.request.PaymentRequest;
import com.katrasolutions.paymentservice.service.PaymentService;
import com.stripe.exception.StripeException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/posts/{id}/boost")
    public PostsModel boostPost(@PathVariable Long id, @RequestBody PaymentRequest paymentRequest, HttpServletRequest httpServletRequest)
            throws StripeException {
        return service.boostPost(id, paymentRequest, httpServletRequest);
    }

    @GetMapping("/posts/boosted")
    public List<PostsModel> findAll() {
        return service.findAllPosts();
    }
}

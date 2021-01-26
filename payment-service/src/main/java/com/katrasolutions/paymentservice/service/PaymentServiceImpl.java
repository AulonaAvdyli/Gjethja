package com.katrasolutions.paymentservice.service;

import com.katrasolutions.paymentservice.entity.Posts;
import com.katrasolutions.paymentservice.mapper.PostsMapper;
import com.katrasolutions.paymentservice.model.PostsModel;
import com.katrasolutions.paymentservice.repository.PostsRepository;
import com.katrasolutions.paymentservice.request.PaymentRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final RestTemplate restTemplate;

    private final PostsRepository postsRepository;

    private final PostsMapper postsMapper;

    public PaymentServiceImpl(PostsMapper postsMapper, PostsRepository postsRepository, RestTemplate restTemplate) {
        this.postsMapper = postsMapper;
        this.postsRepository = postsRepository;
        this.restTemplate = restTemplate;
    }

    @Value("${stripe.secret-key}")
    private String secretApiKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretApiKey;
    }

    @Override
    public PostsModel boostPost(Long id, PaymentRequest request, HttpServletRequest servletRequest) throws StripeException {

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", (int) (request.getAmount() * 100));
        chargeParams.put("currency", "EUR");
        chargeParams.put("source", request.getKey());
        Charge.create(chargeParams);

        PostsModel getPost = getPosts(id, servletRequest);
        getPost.setBoosted(true);
        if (request.getAmount() == 3.0) {
            getPost.setDuration(LocalDate.now().plusDays(3));
        } else if (request.getAmount() == 5.0) {
            getPost.setDuration(LocalDate.now().plusDays(5));
        } else if (request.getAmount() == 7.0) {
            getPost.setDuration(LocalDate.now().plusDays(7));
        } else {
            getPost.setBoosted(false);
        }
        postsRepository.save(postsMapper.modelToEntity(getPost));
        return getPost;
    }

    @Override
    public List<PostsModel> findAllPosts() {
        List<Posts> postsList = postsRepository.findAllByIsBoostedTrue();
        return postsMapper.entitiesToModels(postsList);
    }

    private PostsModel getPosts(Long id, HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorization);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<PostsModel> restExchange =
                restTemplate.exchange("http://main-leaf-service/api/posts/" + id,
                        HttpMethod.GET,
                        httpEntity, new ParameterizedTypeReference<PostsModel>() {
                        });
        return restExchange.getBody();
    }
}

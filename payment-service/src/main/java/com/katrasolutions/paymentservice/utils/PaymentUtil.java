package com.katrasolutions.paymentservice.utils;

import com.katrasolutions.paymentservice.entity.Posts;
import com.katrasolutions.paymentservice.repository.PostsRepository;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PaymentUtil {

    private final PostsRepository repository;
    private final Logger log = LoggerFactory.getLogger(PaymentUtil.class);

    public PaymentUtil(PostsRepository repository) {
        this.repository = repository;
    }

    @Async
    @Scheduled(cron = "0 1 1 * * ?")
    public void updateBoostedPosts() {
        log.info("CRON JOB IS RUNNING");
        repository.findAllByIsBoostedTrue()
                .stream()
                .filter(post -> post.getDuration().isBefore(LocalDate.now()))
                .forEach(Posts::changeBoostedStatus);
    }
}

package com.katrasolutions.paymentservice.repository;

import com.katrasolutions.paymentservice.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Posts, Long> {
    List<Posts> findAllByIsBoostedTrue();
}
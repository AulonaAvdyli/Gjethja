package com.katrasolutions.feedbackservice.repository;

import com.katrasolutions.feedbackservice.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}

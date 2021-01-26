package com.katrasolutions.gjethja.repository;

import com.katrasolutions.gjethja.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}

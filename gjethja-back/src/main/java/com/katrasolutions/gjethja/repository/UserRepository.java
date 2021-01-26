package com.katrasolutions.gjethja.repository;


import com.katrasolutions.gjethja.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository<T extends User> extends JpaRepository<T, Long> {

    T findByEmail(String username);
}
package com.code.decode.spring.security.POC1.repo;

import com.code.decode.spring.security.POC1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}


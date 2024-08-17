package com.devgang.marketduck.domain.user.repository;

import com.devgang.marketduck.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Page<User> findAll(Pageable pageable);
}



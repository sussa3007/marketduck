package com.devgang.marketduck.domain.user.repository;

import com.devgang.marketduck.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}

package com.devgang.marketduck.domain.user.repository;

import com.devgang.marketduck.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findById(Long id) {
        // UserJpaRepository를 사용하여 ID로 사용자 찾기
        return userJpaRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        // UserJpaRepository를 사용하여 이메일로 사용자 찾기
        return userJpaRepository.findByEmail(email);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        // UserJpaRepository를 사용하여 페이징 가능한 모든 사용자 찾기
        return userJpaRepository.findAll(pageable);
    }
}
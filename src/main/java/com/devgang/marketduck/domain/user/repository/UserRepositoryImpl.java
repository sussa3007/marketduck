package com.devgang.marketduck.domain.user.repository;

import com.devgang.marketduck.domain.image.entity.UserImage;
import com.devgang.marketduck.domain.image.repository.UserImageJpaRepository;
import com.devgang.marketduck.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    private final UserImageJpaRepository userImageJpaRepository;


    @Override
    public User saveUser(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        // UserJpaRepository를 사용하여 ID로 사용자 찾기
        return userJpaRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        // UserJpaRepository를 사용하여 이메일로 사용자 찾기
        return userJpaRepository.findByUsername(email);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        // UserJpaRepository를 사용하여 페이징 가능한 모든 사용자 찾기
        return userJpaRepository.findAll(pageable);
    }

    @Override
    public List<UserImage> findAllUserImages(Long userId) {
        return userImageJpaRepository.findAllByUserId(userId);
    }

    @Override
    public UserImage saveUserImage(UserImage userImage) {
        return userImageJpaRepository.save(userImage);
    }

    @Override
    public void deleteUserImage(Long userId) {
        userImageJpaRepository.deleteAllByUserId(userId);
    }
}
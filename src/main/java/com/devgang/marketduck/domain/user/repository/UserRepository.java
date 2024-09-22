package com.devgang.marketduck.domain.user.repository;

import com.devgang.marketduck.domain.image.entity.UserImage;
import com.devgang.marketduck.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User saveUser(User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Page<User> findAll(Pageable pageable);

    List<UserImage> findAllUserImages(Long userId);

    UserImage saveUserImage(UserImage userImage);

    void deleteUserImage(Long userId);

}



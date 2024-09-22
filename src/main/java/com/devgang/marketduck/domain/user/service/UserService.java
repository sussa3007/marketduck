package com.devgang.marketduck.domain.user.service;

import com.devgang.marketduck.api.user.dto.UserResponseDto;
import com.devgang.marketduck.constant.ErrorCode;
import com.devgang.marketduck.constant.LoginType;
import com.devgang.marketduck.constant.UserStatus;
import com.devgang.marketduck.domain.user.entity.User;
import com.devgang.marketduck.domain.user.repository.UserRepository;
import com.devgang.marketduck.exception.ServiceLogicException;
import com.devgang.marketduck.http.service.SocialHttpService;
import com.devgang.marketduck.openapi.user.dto.SocialLoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final SocialHttpService socialHttpService;

    private final PasswordEncoder passwordEncoder;

    public UserResponseDto findUser(Long userId) {
        return UserResponseDto.of(userRepository.findById(userId)
                .orElseThrow(() -> new ServiceLogicException(ErrorCode.NOT_FOUND_USER)));
    }

    public UserResponseDto findUser(String email) {
        return UserResponseDto.of(userRepository.findByEmail(email)
                .orElseThrow(() -> new ServiceLogicException(ErrorCode.NOT_FOUND_USER)));
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ServiceLogicException(ErrorCode.NOT_FOUND_USER));
    }

    public UserResponseDto loginSocialUser(SocialLoginDto dto) {
        String email = socialHttpService.generateLoginRequest(dto);
        UUID uuid = UUID.randomUUID();
        String nickName = uuid.toString().substring(7) + "-User";
        return UserResponseDto.of(createOrVerifyUser(email, nickName, dto.getLoginType()));
    }

    public User createOrVerifyUser(String email, String nickName, LoginType loginType) {
        // 회원 가입 여부 검증 분기
        try {
            User findUser = userRepository.findByEmail(email)
                    .orElseThrow(() -> new ServiceLogicException(ErrorCode.NOT_FOUND_USER));
            if (findUser.getUserStatus().equals(UserStatus.INACTIVE)) {
                throw new ServiceLogicException(ErrorCode.USER_INACTIVE);
            }
            User result = null;
            if (!loginType.equals(findUser.getLoginType())) {
                findUser.setLoginType(loginType);
                result = userRepository.saveUser(findUser);
            } else {
                result = findUser;
            }
            return result;
        } catch (ServiceLogicException e) {
            if (e.getErrorCode().equals(ErrorCode.NOT_FOUND_USER)) {
                UUID uuid = UUID.randomUUID();
                String password = passwordEncoder.encode(uuid.toString());
                User user = User.createSocialUser(email, nickName, password, loginType);
                return userRepository.saveUser(user);
            } else {
                throw e;
            }
        }
    }


}

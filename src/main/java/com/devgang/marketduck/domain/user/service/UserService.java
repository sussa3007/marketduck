package com.devgang.marketduck.domain.user.service;

import com.devgang.marketduck.api.openapi.user.dto.LoginDto;
import com.devgang.marketduck.api.openapi.user.dto.SocialLoginDto;
import com.devgang.marketduck.api.user.dto.UserPatchRequestDto;
import com.devgang.marketduck.api.user.dto.UserResponseDto;
import com.devgang.marketduck.constant.AwsProperty;
import com.devgang.marketduck.constant.ErrorCode;
import com.devgang.marketduck.constant.LoginType;
import com.devgang.marketduck.constant.UserStatus;
import com.devgang.marketduck.domain.image.entity.UserImage;
import com.devgang.marketduck.domain.user.entity.User;
import com.devgang.marketduck.domain.user.repository.UserRepository;
import com.devgang.marketduck.exception.ServiceLogicException;
import com.devgang.marketduck.file.service.FileService;
import com.devgang.marketduck.http.service.SocialHttpService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final SocialHttpService socialHttpService;

    private final PasswordEncoder passwordEncoder;

    private final FileService fileService;

    public Page<UserResponseDto> findAllUser(int page) {
        return userRepository.findAll(PageRequest.of(page, 10, Sort.by("createdAt").descending()))
                .map(UserResponseDto::of);
    }

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

    public User findUserByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ServiceLogicException(ErrorCode.NOT_FOUND_USER));
    }

    public UserResponseDto loginSocialUser(SocialLoginDto dto) {
        String email = socialHttpService.generateLoginRequest(dto);
        UUID uuid = UUID.randomUUID();
        String nickName = uuid.toString().substring(7) + "-User";
        return UserResponseDto.of(createOrVerifyUser(email, nickName, dto.getLoginType()));
    }

    public UserResponseDto loginBasic(LoginDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        User findUser = findUserByEmail(username);
        if (passwordEncoder.matches(password, findUser.getPassword())) {
            return UserResponseDto.of(findUser);
        } else {
            throw new ServiceLogicException(ErrorCode.ACCESS_DENIED);
        }
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

    public UserResponseDto createOrUpdateUserImage(Long userId, MultipartFile[] files) {
        User findUser = findUserByUserId(userId);
        List<UserImage> currentList = userRepository.findAllUserImages(userId);
        if (!currentList.isEmpty()) {
            currentList.forEach( m ->
                    fileService.deleteAwsFile(m.getFileName(), AwsProperty.USER_IMAGE)
            );
        }
        userRepository.deleteUserImage(userId);
        for (MultipartFile file : files) {
            String fileName = UUID.randomUUID().toString();
            String url = fileService.saveMultipartFileForAws(file, AwsProperty.USER_IMAGE, fileName);
            UserImage userImage = UserImage.create(fileName, url, userId);
            userRepository.saveUserImage(userImage);
            findUser.setProfileImageUrl(url);
        }
        return UserResponseDto.of(userRepository.saveUser(findUser));
    }

    public UserResponseDto updateUser(Long userId, UserPatchRequestDto dto) {
        User findUser = findUserByUserId(userId);
        User updateUser = findUser.updateUser(dto);
        return UserResponseDto.of(userRepository.saveUser(updateUser));
    }

    public void deleteUser(Long userId) {
        User findUser = findUserByUserId(userId);
        findUser.setUserStatus(UserStatus.INACTIVE);
        userRepository.saveUser(findUser);
    }


}

package com.devgang.marketduck.api.user.controller;

import com.devgang.marketduck.api.openapi.user.dto.PhoneVerifyRequestDto;
import com.devgang.marketduck.api.openapi.user.dto.VerifyNumRequestDto;
import com.devgang.marketduck.api.user.dto.UserPatchRequestDto;
import com.devgang.marketduck.api.user.dto.UserResponseDto;
import com.devgang.marketduck.domain.user.entity.User;
import com.devgang.marketduck.domain.user.service.UserService;
import com.devgang.marketduck.dto.PageResponseDto;
import com.devgang.marketduck.dto.ResponseDto;
import com.devgang.marketduck.dto.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController implements UserControllerIfs{

    private final UserService userService;

    @Override
    @GetMapping("/list")
    public ResponseEntity<PageResponseDto<List<UserResponseDto>>> getUserList(int page, User user) {
        Page<UserResponseDto> response = userService.findAllUser(page);
        return ResponseEntity.ok(PageResponseDto.of(response, response.getContent(), Result.ok()));
    }

    @Override
    @GetMapping("/{userId}")
    public ResponseEntity<ResponseDto<UserResponseDto>> getUser(Long userId) {
        UserResponseDto response = userService.findUser(userId);
        return ResponseEntity.ok(ResponseDto.of(response, Result.ok()));
    }

    @Override
    @PatchMapping("/{userId}")
    public ResponseEntity<ResponseDto<UserResponseDto>> patchUser(
            Long userId,
            UserPatchRequestDto requestDto,
            User user
    ) {
        UserResponseDto response = userService.updateUser(userId, requestDto);
        return ResponseEntity.ok(ResponseDto.of(response, Result.ok()));
    }

    @Override
    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseDto<?>> deleteUser(Long userId, User user) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(ResponseDto.of(Result.ok()));
    }

    @Override
    @PostMapping("/{userId}/profile-image")
    public ResponseEntity<ResponseDto<UserResponseDto>> postUserImage(
            Long userId,
            MultipartFile[] multipartFile,
            User user
    ) {
        UserResponseDto response = userService.createOrUpdateUserImage(userId, multipartFile);
        return ResponseEntity.ok(ResponseDto.of(response, Result.ok()));
    }

    @Override
    @PostMapping("/verify-phone")
    public ResponseEntity<ResponseDto<?>> verifyPhonePost(PhoneVerifyRequestDto requestDto) {
        return ResponseEntity.ok(ResponseDto.of(Result.ok()));
    }

    @Override
    @PostMapping("/verify-num")
    public ResponseEntity<ResponseDto<?>> verifyNumPost(VerifyNumRequestDto requestDto) {
        return ResponseEntity.ok(ResponseDto.of(Result.ok()));
    }
}

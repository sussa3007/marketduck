package com.devgang.marketduck.api.user.controller;

import com.devgang.marketduck.api.user.dto.UserPatchRequestDto;
import com.devgang.marketduck.api.user.dto.UserResponseDto;
import com.devgang.marketduck.domain.user.entity.User;
import com.devgang.marketduck.dto.PageResponseDto;
import com.devgang.marketduck.dto.ResponseDto;
import com.devgang.marketduck.openapi.user.dto.PhoneVerifyRequestDto;
import com.devgang.marketduck.openapi.user.dto.VerifyNumRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController implements UserControllerIfs{
    @Override
    @GetMapping("/list")
    public ResponseEntity<PageResponseDto<List<UserResponseDto>>> getUserList(int page, User user) {
        return null;
    }

    @Override
    @GetMapping("/{userId}")
    public ResponseEntity<ResponseDto<UserResponseDto>> getUser(Long userId) {
        return null;
    }

    @Override
    @PatchMapping("/{userId}")
    public ResponseEntity<ResponseDto<UserResponseDto>> patchUser(Long userId, UserPatchRequestDto requestDto, User user) {
        return null;
    }

    @Override
    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseDto<?>> deleteUser(Long userId, User user) {
        return null;
    }

    @Override
    @PostMapping("/{userId}/profile-image")
    public ResponseEntity<ResponseDto<UserResponseDto>> postUserImage(Long userId, MultipartFile[] multipartFile, User user) {
        return null;
    }

    @Override
    @PostMapping("/verify-phone")
    public ResponseEntity<ResponseDto<?>> verifyPhonePost(PhoneVerifyRequestDto requestDto) {
        return null;
    }

    @Override
    @PostMapping("/verify-num")
    public ResponseEntity<ResponseDto<?>> verifyNumPost(VerifyNumRequestDto requestDto) {
        return null;
    }
}

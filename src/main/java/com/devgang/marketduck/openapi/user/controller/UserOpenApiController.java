package com.devgang.marketduck.openapi.user.controller;


import com.devgang.marketduck.dto.ResponseDto;
import com.devgang.marketduck.openapi.user.dto.LoginApiResponseDto;
import com.devgang.marketduck.openapi.user.dto.SocialLoginDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open-api/user")
@RequiredArgsConstructor
@Slf4j
public class UserOpenApiController implements UserOpenApiControllerIfs{

    @Override
    @PostMapping("/login")
    public ResponseEntity<ResponseDto<LoginApiResponseDto>> loginSocial(SocialLoginDto loginDto, HttpServletResponse response) {
        return null;
    }

    @Override
    @PostMapping("/reissue-token/{userId}")
    public ResponseEntity<ResponseDto<LoginApiResponseDto>> refreshToken(Long userId) {
        return null;
    }

}

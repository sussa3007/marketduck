package com.devgang.marketduck.openapi.user.controller;


import com.devgang.marketduck.api.user.dto.UserResponseDto;
import com.devgang.marketduck.auth.jwt.JwtTokenizer;
import com.devgang.marketduck.auth.token.Token;
import com.devgang.marketduck.domain.user.service.UserService;
import com.devgang.marketduck.dto.ResponseDto;
import com.devgang.marketduck.dto.Result;
import com.devgang.marketduck.openapi.user.dto.LoginApiResponseDto;
import com.devgang.marketduck.openapi.user.dto.SocialLoginDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open-api/user")
@RequiredArgsConstructor
@Slf4j
public class UserOpenApiController implements UserOpenApiControllerIfs{

    private final JwtTokenizer jwtTokenizer;

    private final UserService userService;

    @Override
    @PostMapping("/login")
    public ResponseEntity<ResponseDto<LoginApiResponseDto>> loginSocial(
            SocialLoginDto loginDto, HttpServletResponse response) {
        UserResponseDto user = userService.loginSocialUser(loginDto);
        Token token = jwtTokenizer.delegateToken(user);
        return ResponseEntity.ok(ResponseDto.of(LoginApiResponseDto.of(token.getAccessToken(), user), Result.ok()));
    }

    @Override
    @GetMapping("/verify-user")
    public ResponseEntity verifyUser(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String accessToken = authorization.replace("Bearer ", "");
        jwtTokenizer.verifyAccessToken(accessToken);
        log.info("# Verify Login User");
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/reissue-token/{userId}")
    public ResponseEntity<ResponseDto<LoginApiResponseDto>> refreshToken(
            HttpServletResponse response,
            Long userId
    ) {
        UserResponseDto user = userService.findUser(userId);
        jwtTokenizer.verifyRefreshToken(user, response);
        Token token = jwtTokenizer.delegateToken(user);
        log.info("# Reissue Token");
        return ResponseEntity.ok(ResponseDto.of(LoginApiResponseDto.of(token.getAccessToken(), user), Result.ok()));
    }

}

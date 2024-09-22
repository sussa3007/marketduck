package com.devgang.marketduck.openapi.user.controller;

import com.devgang.marketduck.dto.ResponseDto;
import com.devgang.marketduck.openapi.user.dto.LoginApiResponseDto;
import com.devgang.marketduck.openapi.user.dto.LoginDto;
import com.devgang.marketduck.openapi.user.dto.SocialLoginDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "User Open API", description = "회원 관련 Open API")

public interface UserOpenApiControllerIfs {

    class LoginResponse extends ResponseDto<LoginApiResponseDto> {}

    @Operation(summary = "소셜 로그인 / 회원가입 요청", description = "바디 데이터 응답 확인")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상 응답", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))
            })
    })
    ResponseEntity<ResponseDto<LoginApiResponseDto>> loginSocial(
            @RequestBody SocialLoginDto loginDto,
            HttpServletResponse response
    );

    @Operation(summary = "일반 회원(임시 API)", description = "최고 관리자 로그인 하기위한 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상 응답", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))
            })
    })
    ResponseEntity<ResponseDto<LoginApiResponseDto>> login(
            @RequestBody LoginDto loginDto,
            HttpServletResponse response
    );

    @Operation(summary = "로그인 회원 검증 API", description = "토큰값을 가지고 회원 검증 요청 API, 토큰이 만료되지 않은 회원이라면 200 응답, 헤더에 Authorization 토큰 값 있어야함")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상 응답", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))
            })
    })
    ResponseEntity verifyUser(
            HttpServletRequest request
    );

    @Operation(summary = "토큰 재발급", description = "바디 데이터 응답 확인")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상 응답", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))
            })
    })
    ResponseEntity<ResponseDto<LoginApiResponseDto>> refreshToken(
            HttpServletResponse response,
            @PathVariable @Parameter(description = "회원의 식별자", required = true) Long userId
    );


}

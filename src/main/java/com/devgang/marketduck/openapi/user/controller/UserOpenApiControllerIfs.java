package com.devgang.marketduck.openapi.user.controller;

import com.devgang.marketduck.dto.ResponseDto;
import com.devgang.marketduck.openapi.user.dto.EmailRequestDto;
import com.devgang.marketduck.openapi.user.dto.LoginApiResponseDto;
import com.devgang.marketduck.openapi.user.dto.SocialLoginDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Operation(summary = "토큰 재발급", description = "바디 데이터 응답 확인")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상 응답", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))
            })
    })
    ResponseEntity<ResponseDto<LoginApiResponseDto>> refreshToken(
            @PathVariable @Parameter(description = "회원의 식별자", required = true) Long userId
    );


}

package com.devgang.marketduck.api.user.controller;

import com.devgang.marketduck.annotation.UserSession;
import com.devgang.marketduck.api.user.dto.UserPatchRequestDto;
import com.devgang.marketduck.api.user.dto.UserResponseDto;
import com.devgang.marketduck.domain.user.entity.User;
import com.devgang.marketduck.dto.PageResponseDto;
import com.devgang.marketduck.dto.ResponseDto;
import com.devgang.marketduck.openapi.user.dto.PhoneVerifyRequestDto;
import com.devgang.marketduck.openapi.user.dto.VerifyNumRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "User API", description = "회원 관련 API")
public interface UserControllerIfs {

    class UserResponse extends ResponseDto<UserResponseDto> {
    }

    class UserListResponse extends PageResponseDto<List<UserResponseDto>> {
    }

    @Operation(summary = "전체 회원 정보 요청(관리자)", description = "전체 회원 리스트 요청")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상 응답",
                    content = {@Content(mediaType = "application/json"
                            , schema = @Schema(implementation = UserListResponse.class)
                    )
                    })
    })
    ResponseEntity<PageResponseDto<List<UserResponseDto>>> getUserList(
            @RequestParam(defaultValue = "0") int page,
            @UserSession @Parameter(hidden = true) User user
    );


    @Operation(summary = "회원 정보 요청(개인, 관리자)", description = "특정 회원의 정보 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상 응답",
                    content = {@Content(mediaType = "application/json"
                            , schema = @Schema(implementation = UserResponse.class)
                    )})
    })
    ResponseEntity<ResponseDto<UserResponseDto>> getUser(
            @PathVariable @Parameter(description = "회원의 식별자", required = true) Long userId
    );

    @Operation(summary = "회원 정보 수정(개인, 관리자)", description = "회원 정보 수정 요청")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상 응답",
                    content = {@Content(mediaType = "application/json"
                            , schema = @Schema(implementation = UserResponse.class)
                    )})
    })
    ResponseEntity<ResponseDto<UserResponseDto>> patchUser(
            @PathVariable @Parameter(description = "회원의 식별자", required = true) Long userId,
            @RequestBody UserPatchRequestDto requestDto,
            @UserSession @Parameter(hidden = true) User user
    );


    @Operation(summary = "회원 턀퇴(개인, 관리자)", description = "회원 탈퇴 요청")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상 응답",
                    content = {@Content(mediaType = "application/json")})
    })
    ResponseEntity<ResponseDto<?>> deleteUser(
            @PathVariable @Parameter(description = "회원의 식별자", required = true) Long userId,
            @UserSession @Parameter(hidden = true) User user
    );


    //PDF 파일 등록
    @Operation(summary = "회원 사진 파일 등록/수정(개인, 관리자)", description = "회원 사진 파일 등록 및 수정(이미 파일이 등록 되어있다면 업로드 파일로 수정됨)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상 응답",
                    content = {@Content(mediaType = "application/json"
                            , schema = @Schema(implementation = UserResponse.class)
                    )})
    })
    ResponseEntity<ResponseDto<UserResponseDto>> postUserImage(
            @PathVariable @Parameter(description = "User 식별자", required = true) Long userId,
            @RequestPart("file") MultipartFile[] multipartFile,
            @UserSession @Parameter(hidden = true) User user
    );


    // 회원 가입 이메일 인증 번호 발송 요청
    @Operation(summary = "휴대폰 번호 인증번호 요청", description = "200 OK 응답 확인, 휴대폰 번호 인증번호 요청")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상 응답", content = {
                    @Content(mediaType = "application/json")
            })
    })
    ResponseEntity<ResponseDto<?>> verifyPhonePost(
            @RequestBody PhoneVerifyRequestDto requestDto
    );

    @Operation(summary = "인증번호 검증 요청", description = "200 OK 응답 확인, 인증번호 검증 요청")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상 응답", content = {
                    @Content(mediaType = "application/json")
            })
    })
    ResponseEntity<ResponseDto<?>> verifyNumPost(
            @RequestBody VerifyNumRequestDto requestDto
    );

}


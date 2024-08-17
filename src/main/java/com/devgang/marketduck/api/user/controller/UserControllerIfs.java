package com.devgang.marketduck.api.user.controller;

import com.devgang.marketduck.annotation.UserSession;
import com.devgang.marketduck.api.user.dto.UserResponseDto;
import com.devgang.marketduck.domain.user.entity.User;
import com.devgang.marketduck.dto.PageResponseDto;
import com.devgang.marketduck.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

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
}


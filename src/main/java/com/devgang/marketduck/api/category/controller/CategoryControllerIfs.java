package com.devgang.marketduck.api.category.controller;


import com.devgang.marketduck.annotation.UserSession;
import com.devgang.marketduck.api.category.dto.CategoryRequestDto;
import com.devgang.marketduck.api.category.dto.CategoryPatchRequestDto;
import com.devgang.marketduck.api.openapi.category.dto.CategoryResponseDto;
import com.devgang.marketduck.domain.user.entity.User;
import com.devgang.marketduck.dto.ResponseDto;
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

@Tag(name = "Category API", description = "Category 관련 API")
public interface CategoryControllerIfs {

    class Category extends ResponseDto<CategoryResponseDto> { }


    /*
    * 카테고리 생성
    * */

    @Operation(summary = "Category 생성(관리자)", description = "Category 생성 요청")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상 응답",
                    content = {@Content(mediaType = "application/json"
                            , schema = @Schema(implementation = Category.class)
                    )
                    })
    })
    ResponseEntity<ResponseDto<CategoryResponseDto>> postCategory(
            @UserSession @Parameter(hidden = true) User user,
            @RequestBody CategoryRequestDto requestDto
    );

    /*
    * 카테고리 수정
    * */
    @Operation(summary = "Category 수정(관리자)", description = "Category 수정 요청")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상 응답",
                    content = {@Content(mediaType = "application/json"
                            , schema = @Schema(implementation = Category.class)
                    )
                    })
    })
    ResponseEntity<ResponseDto<CategoryResponseDto>> patchCategory(
            @UserSession @Parameter(hidden = true) User user,
            @RequestBody CategoryPatchRequestDto requestDto
    );

    /*
    * 장르 카테고리 삭제
    * */
    @Operation(summary = "Genre Category 삭제(관리자)", description = "Genre Category 삭제 요청")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상 응답",
                    content = {@Content(mediaType = "application/json"
                    )
                    })
    })
    ResponseEntity<ResponseDto> deleteGenreCategory(
            @PathVariable Long genreCategoryId,
            @UserSession @Parameter(hidden = true) User user
    );

    /*
     * 굿즈 카테고리 삭제
     * */

    @Operation(summary = "Goods Category 삭제(관리자)", description = "Goods Category 삭제 요청")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상 응답",
                    content = {@Content(mediaType = "application/json"
                    )
                    })
    })
    ResponseEntity<ResponseDto> deleteGoodsCategory(
            @PathVariable Long goodsCategoryId,
            @UserSession @Parameter(hidden = true) User user
    );

}

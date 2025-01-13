package com.devgang.marketduck.api.openapi.category.controller;


import com.devgang.marketduck.api.openapi.category.dto.CategoryResponseDto;
import com.devgang.marketduck.api.openapi.category.dto.CategorySearchDto;
import com.devgang.marketduck.dto.PageResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Tag(name = "Category Open API", description = "Category 관련 Open API")
public interface CategoryOpenApiControllerIfs {

    class CategoryPage extends PageResponseDto<List<CategoryResponseDto>> { }


    /*
    * 전체 카테고리 전체 조회 및 검색
    * */
    @Operation(summary = "전체 Category 정보 요청(회원,관리자)", description = "전체 Category 리스트 검색 요청, Type 스키마 확인하여 구분(GOODS, GENRE), 전체 조회시 categoryName 필드 비운 상태로 Type값(Required)만 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상 응답",
                    content = {@Content(mediaType = "application/json"
                            , schema = @Schema(implementation = CategoryPage.class)
                    )
                    })
    })
    ResponseEntity<PageResponseDto<List<CategoryResponseDto>>> getGoodsCategoryList(
            @ModelAttribute CategorySearchDto requestDto
    );




}

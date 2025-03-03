package com.devgang.marketduck.api.openapi.category.controller;

import com.devgang.marketduck.api.openapi.category.dto.CategoryResponseDto;
import com.devgang.marketduck.api.openapi.category.dto.CategorySearchDto;
import com.devgang.marketduck.domain.category.service.CategoryService;
import com.devgang.marketduck.dto.PageResponseDto;
import com.devgang.marketduck.dto.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/open-api/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryOpenApiController implements CategoryOpenApiControllerIfs{

    private final CategoryService categoryService;

    @Override
    @GetMapping
    public ResponseEntity<PageResponseDto<List<CategoryResponseDto>>> getCategoryList(CategorySearchDto requestDto) {
        Page<CategoryResponseDto> response = categoryService.findAll(requestDto);
        return ResponseEntity.ok(PageResponseDto.of(response, response.getContent(), Result.ok()));
    }
}

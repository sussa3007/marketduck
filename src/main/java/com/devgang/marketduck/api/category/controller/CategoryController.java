package com.devgang.marketduck.api.category.controller;

import com.devgang.marketduck.api.category.dto.CategoryPatchRequestDto;
import com.devgang.marketduck.api.category.dto.CategoryRequestDto;
import com.devgang.marketduck.api.openapi.category.dto.CategoryResponseDto;
import com.devgang.marketduck.domain.category.service.CategoryService;
import com.devgang.marketduck.domain.user.entity.User;
import com.devgang.marketduck.dto.ResponseDto;
import com.devgang.marketduck.dto.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController implements CategoryControllerIfs{

    private final CategoryService categoryService;

    @Override
    @PostMapping
    public ResponseEntity<ResponseDto<CategoryResponseDto>> postCategory(User user, CategoryRequestDto requestDto) {
        CategoryResponseDto response = categoryService.createCategory(requestDto, user);
        return ResponseEntity.ok(ResponseDto.of(response, Result.ok()));
    }

    @Override
    @PatchMapping
    public ResponseEntity<ResponseDto<CategoryResponseDto>> patchCategory(User user, CategoryPatchRequestDto requestDto) {
        CategoryResponseDto response = categoryService.updateCategory(requestDto, user);
        return ResponseEntity.ok(ResponseDto.of(response, Result.ok()));
    }

    @Override
    @DeleteMapping("/{genreCategoryId}")
    public ResponseEntity<ResponseDto> deleteGenreCategory(Long genreCategoryId, User user) {
        categoryService.deleteGenreCategory(genreCategoryId, user);
        return ResponseEntity.ok(ResponseDto.of(Result.ok()));
    }

    @Override
    @DeleteMapping("/{goodsCategoryId}")
    public ResponseEntity<ResponseDto> deleteGoodsCategory(Long goodsCategoryId, User user) {
        categoryService.deleteGoodsCategory(goodsCategoryId, user);
        return ResponseEntity.ok(ResponseDto.of(Result.ok()));
    }
}

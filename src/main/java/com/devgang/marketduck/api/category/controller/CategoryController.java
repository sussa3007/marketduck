package com.devgang.marketduck.api.category.controller;

import com.devgang.marketduck.api.category.dto.CategoryPatchRequestDto;
import com.devgang.marketduck.api.category.dto.CategoryRequestDto;
import com.devgang.marketduck.api.openapi.category.dto.CategoryResponseDto;
import com.devgang.marketduck.domain.user.entity.User;
import com.devgang.marketduck.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController implements CategoryControllerIfs{
    @Override
    @PostMapping
    public ResponseEntity<ResponseDto<CategoryResponseDto>> postCategory(User user, CategoryRequestDto requestDto) {
        return null;
    }

    @Override
    @PatchMapping
    public ResponseEntity<ResponseDto<CategoryResponseDto>> patchCategory(User user, CategoryPatchRequestDto requestDto) {
        return null;
    }

    @Override
    @DeleteMapping("/{genreCategoryId}")
    public ResponseEntity<ResponseDto> deleteGenreCategory(Long genreCategoryId, User user) {
        return null;
    }

    @Override
    @DeleteMapping("/{goodsCategoryId}")
    public ResponseEntity<ResponseDto> deleteGoodsCategory(Long goodsCategoryId, User user) {
        return null;
    }
}

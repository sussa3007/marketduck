package com.devgang.marketduck.domain.category.service;

import com.devgang.marketduck.api.category.dto.CategoryPatchRequestDto;
import com.devgang.marketduck.api.category.dto.CategoryRequestDto;
import com.devgang.marketduck.api.openapi.category.dto.CategoryResponseDto;
import com.devgang.marketduck.api.openapi.category.dto.CategorySearchDto;
import com.devgang.marketduck.constant.CategoryType;
import com.devgang.marketduck.constant.ErrorCode;
import com.devgang.marketduck.domain.category.entity.GenreCategory;
import com.devgang.marketduck.domain.category.entity.GoodsCategory;
import com.devgang.marketduck.domain.category.repository.CategoryRepository;
import com.devgang.marketduck.domain.user.entity.User;
import com.devgang.marketduck.exception.ServiceLogicException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Page<CategoryResponseDto> findAll(CategorySearchDto dto) {
        return categoryRepository.findAll(dto);
    }

    public CategoryResponseDto createCategory(CategoryRequestDto dto, User user) {
        String categoryName = dto.getCategoryName();
        CategoryType categoryType = dto.getCategoryType();

        if (categoryName == null || categoryName.isEmpty()) {
            throw new ServiceLogicException(ErrorCode.INVALID_INPUT, "카테고리 이름은 필수 입력 사항입니다.");
        }

        // 1. 카테고리 타입에 따른 분기 처리
        if (categoryType == CategoryType.GENRE) {
            // 1-1. GenreCategory 생성 및 저장
            GenreCategory genreCategory = GenreCategory.create(categoryName);

            GenreCategory savedCategory = categoryRepository.save(genreCategory);

            // 1-2. Response DTO 반환
            return CategoryResponseDto.of(savedCategory.getGenreCategoryId(), savedCategory.getGenreCategoryName(), CategoryType.GENRE);

        } else if (categoryType == CategoryType.GOODS) {
            // 2-1. GoodsCategory 생성 및 저장
            GoodsCategory goodsCategory = GoodsCategory.create(categoryName);

            GoodsCategory savedCategory = categoryRepository.save(goodsCategory);

            // 2-2. Response DTO 반환
            return CategoryResponseDto.of(savedCategory.getGoodsCategoryId(), savedCategory.getGoodsCategoryName(), CategoryType.GOODS);
        }

        // 3. 잘못된 카테고리 타입 예외 처리
        throw new ServiceLogicException(ErrorCode.INVALID_INPUT, "올바르지 않은 카테고리 타입입니다.");
    }

    public CategoryResponseDto updateCategory(CategoryPatchRequestDto dto, User user) {
        Long categoryId = dto.getCategoryId();
        String updatedName = dto.getCategoryName();
        CategoryType categoryType = dto.getCategoryType();

        if (updatedName == null || updatedName.isEmpty()) {
            throw new ServiceLogicException(ErrorCode.INVALID_INPUT, "카테고리 이름은 필수 입력 사항입니다.");
        }

        if (categoryType == CategoryType.GENRE) {
            // GenreCategory 업데이트
            GenreCategory genreCategory = categoryRepository.findOneByGenreCategoryId(categoryId);
            if (genreCategory == null) {
                throw new ServiceLogicException(ErrorCode.NOT_FOUND, "해당 장르 카테고리를 찾을 수 없습니다.");
            }

            genreCategory.setGenreCategoryName(updatedName);
            GenreCategory savedCategory = categoryRepository.save(genreCategory);

            return CategoryResponseDto.of(savedCategory.getGenreCategoryId(), savedCategory.getGenreCategoryName(), CategoryType.GENRE);

        } else if (categoryType == CategoryType.GOODS) {
            // GoodsCategory 업데이트
            GoodsCategory goodsCategory = categoryRepository.findOneByGoodsCategoryId(categoryId);
            if (goodsCategory == null) {
                throw new ServiceLogicException(ErrorCode.NOT_FOUND, "해당 상품 카테고리를 찾을 수 없습니다.");
            }

            goodsCategory.setGoodsCategoryName(updatedName);
            GoodsCategory savedCategory = categoryRepository.save(goodsCategory);

            return CategoryResponseDto.of(savedCategory.getGoodsCategoryId(), savedCategory.getGoodsCategoryName(), CategoryType.GOODS);
        }

        throw new ServiceLogicException(ErrorCode.INVALID_INPUT, "올바르지 않은 카테고리 타입입니다.");
    }

    public void deleteGenreCategory(Long genreCategoryId, User user) {
        GenreCategory genreCategory = categoryRepository.findOneByGenreCategoryId(genreCategoryId);
        if (genreCategory == null) {
            throw new ServiceLogicException(ErrorCode.NOT_FOUND, "해당 장르 카테고리를 찾을 수 없습니다.");
        }

        categoryRepository.deleteGenreCategory(genreCategoryId);
    }

    public void deleteGoodsCategory(Long goodsCategoryId, User user) {
        GoodsCategory goodsCategory = categoryRepository.findOneByGoodsCategoryId(goodsCategoryId);
        if (goodsCategory == null) {
            throw new ServiceLogicException(ErrorCode.NOT_FOUND, "해당 상품 카테고리를 찾을 수 없습니다.");
        }

        categoryRepository.deleteGoodsCategory(goodsCategoryId);
    }
}

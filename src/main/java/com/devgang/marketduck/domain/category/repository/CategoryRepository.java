package com.devgang.marketduck.domain.category.repository;

import com.devgang.marketduck.api.openapi.category.dto.CategoryResponseDto;
import com.devgang.marketduck.api.openapi.category.dto.CategorySearchDto;
import com.devgang.marketduck.domain.category.entity.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryRepository {

    Page<CategoryResponseDto> findAll(CategorySearchDto dto);

    List<GenreCategory> findAllByGenreCategoryIdIn(List<Long> genreCategoryIds);
    List<GoodsCategory> findAllByGoodsCategoryIdIn(List<Long> goodsCategoryIds);

    GenreCategory findOneByGenreCategoryId(Long genreCategoryId);
    GoodsCategory findOneByGoodsCategoryId(Long goodsCategoryId);


    FeedGoodsCategory save(FeedGoodsCategory feedGoodsCategory);
    FeedGenreCategory save(FeedGenreCategory feedGenreCategory);

    GoodsCategory save(GoodsCategory goodsCategory);
    GenreCategory save(GenreCategory genreCategory);
    void deleteGoodsCategory(Long goodsCategoryId);
    void deleteGenreCategory(Long genreCategoryId);
    void deleteFeedGoodsCategory(Long feedGoodsCategoryId);
    void deleteFeedGenreCategory(Long feedGenreCategoryId);
    void deleteAllFeedGoodsCategoryByFeedId(Long feedId);
    void deleteAllFeedGenreCategoryByFeedId(Long feedId);

    UserGoodsCategory save(UserGoodsCategory userGoodsCategory);
    UserGenreCategory save(UserGenreCategory userGenreCategory);
    void deleteUserGoodsCategory(Long userGoodsCategoryId);
    void deleteUserGenreCategory(Long userGenreCategoryId);

    void deleteAllUserGoodsCategoryByUserId(Long userId);
    void deleteAllUserGenreCategoryByUserId(Long userId);
}

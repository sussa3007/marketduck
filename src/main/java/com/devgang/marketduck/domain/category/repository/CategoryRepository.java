package com.devgang.marketduck.domain.category.repository;

import com.devgang.marketduck.domain.category.entity.FeedGenreCategory;
import com.devgang.marketduck.domain.category.entity.FeedGoodsCategory;
import com.devgang.marketduck.domain.category.entity.GenreCategory;
import com.devgang.marketduck.domain.category.entity.GoodsCategory;

import java.util.List;

public interface CategoryRepository {

    List<GenreCategory> findAllByGenreCategoryIdIn(List<Long> genreCategoryIds);
    List<GoodsCategory> findAllByGoodsCategoryIdIn(List<Long> goodsCategoryIds);


    FeedGoodsCategory save(FeedGoodsCategory feedGoodsCategory);
    FeedGenreCategory save(FeedGenreCategory feedGenreCategory);

    GoodsCategory save(GoodsCategory goodsCategory);
    GenreCategory save(GenreCategory genreCategory);
    void deleteGoodsCategory(Long goodsCategoryId);
    void deleteGenreCategory(Long genreCategoryId);
    void deleteFeedGoodsCategory(Long feedGoodsCategoryId);
    void deleteFeedGenreCategory(Long feedGenreCategoryId);
}

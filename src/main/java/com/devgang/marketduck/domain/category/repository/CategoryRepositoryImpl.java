package com.devgang.marketduck.domain.category.repository;

import com.devgang.marketduck.domain.category.entity.FeedGenreCategory;
import com.devgang.marketduck.domain.category.entity.FeedGoodsCategory;
import com.devgang.marketduck.domain.category.entity.GenreCategory;
import com.devgang.marketduck.domain.category.entity.GoodsCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository{

    private final JPAQueryFactory queryFactory;

    private final GenreCategoryJpaRepository genreCategoryJpaRepository;
    private final GoodsCategoryJpaRepository goodsCategoryJpaRepository;
    private final FeedGoodsCategoryJpaRepository feedGoodsCategoryJpaRepository;
    private final FeedGenreCategoryJpaRepository feedGenreCategoryJpaRepository;

    @Override
    public List<GenreCategory> findAllByGenreCategoryIdIn(List<Long> genreCategoryIds) {
        return List.of();
    }

    @Override
    public List<GoodsCategory> findAllByGoodsCategoryIdIn(List<Long> goodsCategoryIds) {
        return List.of();
    }

    @Override
    public FeedGoodsCategory save(FeedGoodsCategory feedGoodsCategory) {
        return null;
    }

    @Override
    public FeedGenreCategory save(FeedGenreCategory feedGenreCategory) {
        return null;
    }

    @Override
    public GoodsCategory save(GoodsCategory goodsCategory) {
        return null;
    }

    @Override
    public GenreCategory save(GenreCategory genreCategory) {
        return null;
    }

    @Override
    public void deleteGoodsCategory(Long goodsCategoryId) {

    }

    @Override
    public void deleteGenreCategory(Long genreCategoryId) {

    }

    @Override
    public void deleteFeedGoodsCategory(Long feedGoodsCategoryId) {

    }

    @Override
    public void deleteFeedGenreCategory(Long feedGenreCategoryId) {

    }
}

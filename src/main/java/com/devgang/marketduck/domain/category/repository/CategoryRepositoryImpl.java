package com.devgang.marketduck.domain.category.repository;

import com.devgang.marketduck.api.openapi.category.dto.CategoryResponseDto;
import com.devgang.marketduck.api.openapi.category.dto.CategorySearchDto;
import com.devgang.marketduck.api.openapi.category.dto.QCategoryResponseDto;
import com.devgang.marketduck.constant.CategoryType;
import com.devgang.marketduck.domain.category.entity.*;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JPAQueryFactory queryFactory;

    private final GenreCategoryJpaRepository genreCategoryJpaRepository;
    private final GoodsCategoryJpaRepository goodsCategoryJpaRepository;
    private final FeedGoodsCategoryJpaRepository feedGoodsCategoryJpaRepository;
    private final FeedGenreCategoryJpaRepository feedGenreCategoryJpaRepository;
    private final UserGoodsCategoryJpaRepository userGoodsCategoryJpaRepository;
    private final UserGenreCategoryJpaRepository userGenreCategoryJpaRepository;

    QGoodsCategory goodsCategory = QGoodsCategory.goodsCategory;
    QGenreCategory genreCategory = QGenreCategory.genreCategory;
    QFeedGoodsCategory feedGoodsCategory = QFeedGoodsCategory.feedGoodsCategory;
    QFeedGenreCategory feedGenreCategory = QFeedGenreCategory.feedGenreCategory;
    QUserGoodsCategory userGoodsCategory = QUserGoodsCategory.userGoodsCategory;
    QUserGenreCategory userGenreCategory = QUserGenreCategory.userGenreCategory;

    @Override
    public Page<CategoryResponseDto> findAll(CategorySearchDto dto) {
        String categoryName = dto.getCategoryName();
        CategoryType categoryType = dto.getCategoryType();
        int size = dto.getSize();
        int page = dto.getPage();
        boolean isPopular = dto.isPopular();

        // 1. Pageable 설정
        Pageable pageable = PageRequest.of(page, size);

        // 2. QueryDSL 쿼리 빌드
        JPAQuery<CategoryResponseDto> query;

        if (categoryType == CategoryType.GENRE) {
            // 장르 카테고리의 경우
            query = queryFactory.select(new QCategoryResponseDto(
                    genreCategory.genreCategoryId,
                    genreCategory.genreCategoryName,
                    Expressions.constant(categoryType),
                    userGenreCategory.count()))
                    .from(genreCategory)
                    .leftJoin(userGenreCategory)
                    .on(genreCategory.genreCategoryId.eq(userGenreCategory.genreCategory.genreCategoryId))
                    .groupBy(genreCategory.genreCategoryId);

            // 검색 조건 추가
            if (categoryName != null && !categoryName.isEmpty()) {
                query.where(genreCategory.genreCategoryName.containsIgnoreCase(categoryName));
            }

            // 인기순 정렬
            if (isPopular) {
                query.orderBy(userGenreCategory.count().desc());
            }
        } else {
            // 상품 카테고리의 경우
            query = queryFactory.select(new QCategoryResponseDto(
                    goodsCategory.goodsCategoryId,
                    goodsCategory.goodsCategoryName,
                    Expressions.constant(categoryType),
                    userGoodsCategory.count()))
                    .from(goodsCategory)
                    .leftJoin(userGoodsCategory)
                    .on(goodsCategory.goodsCategoryId.eq(userGoodsCategory.goodsCategory.goodsCategoryId))
                    .groupBy(goodsCategory.goodsCategoryId);

            // 검색 조건 추가
            if (categoryName != null && !categoryName.isEmpty()) {
                query.where(goodsCategory.goodsCategoryName.containsIgnoreCase(categoryName));
            }

            // 인기순 정렬
            if (isPopular) {
                query.orderBy(userGoodsCategory.count().desc());
            }
        }

        // 3. 페이징 처리
        long total = query.fetchCount();
        List<CategoryResponseDto> content = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public List<GenreCategory> findAllByGenreCategoryIdIn(List<Long> genreCategoryIds) {
        if (genreCategoryIds == null || genreCategoryIds.isEmpty()) {
            return List.of();
        }

        return queryFactory.selectFrom(genreCategory)
                .where(genreCategory.genreCategoryId.in(genreCategoryIds))
                .fetch();
    }

    @Override
    public List<GoodsCategory> findAllByGoodsCategoryIdIn(List<Long> goodsCategoryIds) {
        if (goodsCategoryIds == null || goodsCategoryIds.isEmpty()) {
            return List.of();
        }

        return queryFactory.selectFrom(goodsCategory)
                .where(goodsCategory.goodsCategoryId.in(goodsCategoryIds))
                .fetch();
    }

    @Override
    public GenreCategory findOneByGenreCategoryId(Long genreCategoryId) {
        return queryFactory.selectFrom(genreCategory)
                .where(genreCategory.genreCategoryId.eq(genreCategoryId))
                .fetchOne();
    }

    @Override
    public GoodsCategory findOneByGoodsCategoryId(Long goodsCategoryId) {
        return queryFactory.selectFrom(goodsCategory)
                .where(goodsCategory.goodsCategoryId.eq(goodsCategoryId))
                .fetchOne();
    }

    @Override
    public FeedGoodsCategory save(FeedGoodsCategory feedGoodsCategory) {
        return feedGoodsCategoryJpaRepository.save(feedGoodsCategory);
    }

    @Override
    public FeedGenreCategory save(FeedGenreCategory feedGenreCategory) {
        return feedGenreCategoryJpaRepository.save(feedGenreCategory);
    }

    @Override
    public GoodsCategory save(GoodsCategory goodsCategory) {
        return goodsCategoryJpaRepository.save(goodsCategory);
    }

    @Override
    public GenreCategory save(GenreCategory genreCategory) {
        return genreCategoryJpaRepository.save(genreCategory);
    }

    @Override
    public void deleteGoodsCategory(Long goodsCategoryId) {
        goodsCategoryJpaRepository.deleteById(goodsCategoryId);
    }

    @Override
    public void deleteGenreCategory(Long genreCategoryId) {
        genreCategoryJpaRepository.deleteById(genreCategoryId);
    }

    @Override
    public void deleteFeedGoodsCategory(Long feedGoodsCategoryId) {
        feedGoodsCategoryJpaRepository.deleteById(feedGoodsCategoryId);
    }

    @Override
    public void deleteFeedGenreCategory(Long feedGenreCategoryId) {
        feedGenreCategoryJpaRepository.deleteById(feedGenreCategoryId);
    }

    @Override
    public void deleteAllFeedGoodsCategoryByFeedId(Long feedId) {
        feedGoodsCategoryJpaRepository.deleteAllByFeed_FeedId(feedId);
    }

    @Override
    public void deleteAllFeedGenreCategoryByFeedId(Long feedId) {
        feedGenreCategoryJpaRepository.deleteAllByFeed_FeedId(feedId);
    }

    @Override
    public UserGoodsCategory save(UserGoodsCategory userGoodsCategory) {
        return userGoodsCategoryJpaRepository.save(userGoodsCategory);
    }

    @Override
    public UserGenreCategory save(UserGenreCategory userGenreCategory) {
        return userGenreCategoryJpaRepository.save(userGenreCategory);

    }

    @Override
    public void deleteUserGoodsCategory(Long userGoodsCategoryId) {
        userGoodsCategoryJpaRepository.deleteById(userGoodsCategoryId);
    }

    @Override
    public void deleteUserGenreCategory(Long userGenreCategoryId) {
        userGenreCategoryJpaRepository.deleteById(userGenreCategoryId);
    }

    @Override
    public void deleteAllUserGoodsCategoryByUserId(Long userId) {
        userGoodsCategoryJpaRepository.deleteByUser_UserId(userId);
    }

    @Override
    public void deleteAllUserGenreCategoryByUserId(Long userId) {
        userGenreCategoryJpaRepository.deleteByUser_UserId(userId);
    }
}

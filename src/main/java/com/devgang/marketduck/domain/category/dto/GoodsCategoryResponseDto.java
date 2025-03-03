package com.devgang.marketduck.domain.category.dto;

import com.devgang.marketduck.domain.category.entity.GoodsCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsCategoryResponseDto {
    private Long goodsCategoryId;
    private String name;

    public static GoodsCategoryResponseDto from(GoodsCategory goodsCategory) {
        return GoodsCategoryResponseDto.builder()
                .goodsCategoryId(goodsCategory.getGoodsCategoryId())
                .name(goodsCategory.getGoodsCategoryName())
                .build();
    }
}
package com.devgang.marketduck.domain.category.dto;

import com.devgang.marketduck.domain.category.entity.GenreCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenreCategoryResponseDto {
    private Long genreCategoryId;
    private String name;

    public static GenreCategoryResponseDto from(GenreCategory genreCategory) {
        return GenreCategoryResponseDto.builder()
                .genreCategoryId(genreCategory.getGenreCategoryId())
                .name(genreCategory.getGenreCategoryName())
                .build();
    }
}
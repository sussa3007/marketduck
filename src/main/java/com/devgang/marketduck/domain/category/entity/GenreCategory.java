package com.devgang.marketduck.domain.category.entity;


import com.devgang.marketduck.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "genre_category")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenreCategory extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreCategoryId;

    @Column(nullable = false, unique = true)
    private String genreCategoryName;

    // FeedGenreCategory 연관관계 설정
    @ToString.Exclude
    @OneToMany(mappedBy = "genreCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FeedGenreCategory> feedGenreCategories = new LinkedHashSet<>();

    public void addFeedGenreCategory(FeedGenreCategory feedGenreCategory) {
        feedGenreCategories.add(feedGenreCategory);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "genreCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserGenreCategory> userGenreCategories = new LinkedHashSet<>();

    public void addUserGenreCategory(UserGenreCategory userGenreCategory) {
        userGenreCategories.add(userGenreCategory);
    }
    public static GenreCategory create(String genreCategoryName) {
        return GenreCategory.builder()
                .genreCategoryName(genreCategoryName)
                .feedGenreCategories(new LinkedHashSet<>())
                .build();
    }
}

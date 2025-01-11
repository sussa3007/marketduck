package com.devgang.marketduck.domain.category.entity;

import com.devgang.marketduck.audit.Auditable;
import com.devgang.marketduck.domain.feed.entity.Feed;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedGenreCategory extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedGenreCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_category_id", nullable = false)
    private GenreCategory genreCategory;

    public void addFeed(Feed feed) {
        this.feed = feed;
        feed.addFeedGenreCategory(this);
    }

    public void addGenreCategory(GenreCategory genreCategory) {
        this.genreCategory = genreCategory;
        genreCategory.addFeedGenreCategory(this);
    }

    public static FeedGenreCategory create(Feed feed, GenreCategory genreCategory) {
        FeedGenreCategory feedGenreCategory = new FeedGenreCategory();
        feedGenreCategory.addFeed(feed);
        feedGenreCategory.addGenreCategory(genreCategory);
        return feedGenreCategory;
    }
}

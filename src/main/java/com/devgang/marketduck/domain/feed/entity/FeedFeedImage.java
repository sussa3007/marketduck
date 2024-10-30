package com.devgang.marketduck.domain.feed.entity;

import com.devgang.marketduck.audit.Auditable;
import com.devgang.marketduck.domain.image.entity.FeedImage;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedFeedImage extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedFeedImageId;

    @ManyToOne
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;

    @ManyToOne
    @JoinColumn(name = "feed_image_id", nullable = false)
    private FeedImage feedImage;

    public void addFeedImage(FeedImage feedImage) {
        this.feedImage = feedImage;
        feedImage.addFeedFeedImage(this);
    }

    public void addFeed(Feed feed) {
        this.feed = feed;
        feed.addFeedFeedImage(this);
    }
}

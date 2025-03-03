package com.devgang.marketduck.domain.feed.entity;

import com.devgang.marketduck.audit.Auditable;
import com.devgang.marketduck.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "feed_likes", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "feed_id", "user_id" })
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedLike extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    public static FeedLike create(Feed feed, User user) {
        return FeedLike.builder()
                .feed(feed)
                .user(user)
                .build();
    }
}
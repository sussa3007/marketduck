package com.devgang.marketduck.domain.image.entity;


import com.devgang.marketduck.audit.Auditable;
import com.devgang.marketduck.domain.feed.entity.FeedFeedImage;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "user_images")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedImage extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedImageId;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileUrl;

    @ToString.Exclude
    @OneToMany(mappedBy = "feedImage", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FeedFeedImage> feedFeedImages = new LinkedHashSet<>();

    public void addFeedFeedImage(FeedFeedImage feedFeedImage) {
        feedFeedImages.add(feedFeedImage);
    }
}

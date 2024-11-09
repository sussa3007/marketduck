package com.devgang.marketduck.domain.image.entity;


import com.devgang.marketduck.audit.Auditable;
import com.devgang.marketduck.domain.feed.entity.Feed;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false)
    private int imageIndex;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;

}

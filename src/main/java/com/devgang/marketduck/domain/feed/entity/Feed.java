package com.devgang.marketduck.domain.feed.entity;


import com.devgang.marketduck.audit.Auditable;
import com.devgang.marketduck.constant.FeedStatus;
import com.devgang.marketduck.domain.image.entity.FeedImage;
import com.devgang.marketduck.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "feeds")  // 테이블 이름을 "feeds"로 지정
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Feed extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedId;  // ID 필드를 feedId로 변경

    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    @Column(nullable = false)
    private int viewCount;


    @Column(nullable = false)
    private int likeCount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FeedStatus feedStatus;

    @ToString.Exclude
    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FeedImage> feedImages = new LinkedHashSet<>();

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    // FeedGoodsCategory 연관관계 설정
    @ToString.Exclude
    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FeedGoodsCategory> feedGoodsCategories = new LinkedHashSet<>();

    // FeedGenreCategory 연관관계 설정
    @ToString.Exclude
    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FeedGenreCategory> feedGenreCategories = new LinkedHashSet<>();


    public void addFeedImage(FeedImage feedImage) {
        feedImages.add(feedImage);
        feedImage.setFeed(this);
    }

    public void addUser(User user) {
        this.user = user;
        user.addFeed(this);
    }

    public void addFeedGoodsCategory(FeedGoodsCategory feedGoodsCategory) {
        feedGoodsCategories.add(feedGoodsCategory);
    }

    public void addFeedGenreCategory(FeedGenreCategory feedGenreCategory) {
        feedGenreCategories.add(feedGenreCategory);
    }

}
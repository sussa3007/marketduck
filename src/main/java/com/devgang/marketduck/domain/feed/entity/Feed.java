package com.devgang.marketduck.domain.feed.entity;


import com.devgang.marketduck.audit.Auditable;
import com.devgang.marketduck.constant.FeedStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "feeds")  // 테이블 이름을 "feeds"로 지정
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Feed extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedId;  // ID 필드를 feedId로 변경

    @Column(nullable = false)
    private String genreCategory;

    @Column(nullable = false)
    private String goodsCategory;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    @Column(nullable = false)
    private int viewCount;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FeedStatus authority;


}
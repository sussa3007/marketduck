package com.devgang.marketduck.domain.feed.entity;


import com.devgang.marketduck.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;

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
    private String genre;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false, length = 1000)
    private String content;
}
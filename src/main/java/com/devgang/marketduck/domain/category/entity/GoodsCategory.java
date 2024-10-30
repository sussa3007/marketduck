package com.devgang.marketduck.domain.category.entity;

import com.devgang.marketduck.audit.Auditable;
import com.devgang.marketduck.domain.feed.entity.FeedGoodsCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "goods_category")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsCategory extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String goodsCategoryId;

    @Column(nullable = false)
    private String goodsCategory;

    @ToString.Exclude
    @OneToMany(mappedBy = "goodsCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FeedGoodsCategory> feedGoodsCategories = new LinkedHashSet<>();

    public void addFeedGoodsCategory(FeedGoodsCategory feedGoodsCategory) {
        feedGoodsCategories.add(feedGoodsCategory);
    }
}

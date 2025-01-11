package com.devgang.marketduck.domain.category.entity;


import com.devgang.marketduck.audit.Auditable;
import com.devgang.marketduck.domain.feed.entity.Feed;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedGoodsCategory extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedGoodsCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_category_id", nullable = false)
    private GoodsCategory goodsCategory;

    public void addFeed(Feed feed) {
        this.feed = feed;
        feed.addFeedGoodsCategory(this);
    }

    public void addGoodsCategory(GoodsCategory goodsCategory) {
        this.goodsCategory = goodsCategory;
        goodsCategory.addFeedGoodsCategory(this);
    }
    public static FeedGoodsCategory create(Feed feed, GoodsCategory goodsCategory) {
        FeedGoodsCategory feedGoodsCategory = new FeedGoodsCategory();
        feedGoodsCategory.addFeed(feed);
        feedGoodsCategory.addGoodsCategory(goodsCategory);
        return feedGoodsCategory;
    }
}

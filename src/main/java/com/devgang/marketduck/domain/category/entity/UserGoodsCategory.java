package com.devgang.marketduck.domain.category.entity;


import com.devgang.marketduck.audit.Auditable;
import com.devgang.marketduck.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGoodsCategory extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userGoodsCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_category_id", nullable = false)
    private GoodsCategory goodsCategory;

    public void addUser(User user) {
        this.user = user;
        user.addUserGoodsCategory(this);
    }

    public void addGoodsCategory(GoodsCategory goodsCategory) {
        this.goodsCategory = goodsCategory;
        goodsCategory.addUserGoodsCategory(this);
    }
    public static UserGoodsCategory create(User user, GoodsCategory goodsCategory) {
        UserGoodsCategory userGoodsCategory = new UserGoodsCategory();
        userGoodsCategory.addUser(user);
        userGoodsCategory.addGoodsCategory(goodsCategory);
        return userGoodsCategory;
    }
}

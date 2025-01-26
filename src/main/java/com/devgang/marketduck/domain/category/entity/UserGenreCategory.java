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
public class UserGenreCategory extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userGenreCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_category_id", nullable = false)
    private GenreCategory genreCategory;

    public void addUser(User user) {
        this.user = user;
        user.addUserGenreCategory(this);
    }

    public void addGenreCategory(GenreCategory genreCategory) {
        this.genreCategory = genreCategory;
        genreCategory.addUserGenreCategory(this);
    }
    public static UserGenreCategory create(User user, GenreCategory genreCategory) {
        UserGenreCategory userGenreCategory = new UserGenreCategory();
        userGenreCategory.addUser(user);
        userGenreCategory.addGenreCategory(genreCategory);
        return userGenreCategory;
    }
}

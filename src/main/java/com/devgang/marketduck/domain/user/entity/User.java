package com.devgang.marketduck.domain.user.entity;


import com.devgang.marketduck.api.user.dto.UserPatchRequestDto;
import com.devgang.marketduck.audit.Auditable;
import com.devgang.marketduck.constant.Authority;
import com.devgang.marketduck.constant.LoginType;
import com.devgang.marketduck.constant.UserStatus;
import com.devgang.marketduck.domain.category.entity.UserGenreCategory;
import com.devgang.marketduck.domain.category.entity.UserGoodsCategory;
import com.devgang.marketduck.domain.feed.entity.Feed;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Setter
@Getter
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;


    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = true, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String profileImageUrl;

    /*
    * 인증 관련 체크
    * */
    @Column(nullable = false)
    private Boolean emailVerified;

    @Column(nullable = false)
    private Boolean phoneVerified;


    /*
    * 회원 상태
    * */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LoginType loginType;


    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();


    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Feed> feeds = new LinkedHashSet<>();

    public void addFeed(Feed feed) {
        feeds.add(feed);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserGoodsCategory> userGoodsCategories = new LinkedHashSet<>();

    public void addUserGoodsCategory(UserGoodsCategory userGoodsCategory) {
        userGoodsCategories.add(userGoodsCategory);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserGenreCategory> userGenreCategories = new LinkedHashSet<>();

    public void addUserGenreCategory(UserGenreCategory userGenreCategory) {
        userGenreCategories.add(userGenreCategory);
    }



    public static User createSocialUser(String email, String nickName, String password, LoginType loginType) {
        return User.builder()
                .username(email)
                .phoneNumber(UUID.randomUUID().toString().substring(0, 8))
                .profileImageUrl("")
                .nickname(nickName)
                .password(password)
                .loginType(loginType)
                .userStatus(UserStatus.WAIT_INFO)
                .authority(Authority.USER)
                .emailVerified(true)
                .phoneVerified(false)
                .roles(Authority.USER.getStringRole())
                .build();
    }

    public User updateUser(UserPatchRequestDto dto) {
        this.nickname = Optional.ofNullable(dto.getNickname()).orElse(this.nickname);
        Optional<String> phNum = Optional.ofNullable(dto.getPhoneNumber());
        if (phNum.isPresent()) {
            this.phoneNumber = phNum.get();
            this.phoneVerified = false;
        }
        Optional<Authority> authProperty = Optional.ofNullable(dto.getAuthority());
        if (authProperty.isPresent()) {
            this.authority = authProperty.get();
            this.roles = authProperty.get().getStringRole();
        }
        return this;
    }
}

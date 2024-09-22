package com.devgang.marketduck.domain.image.entity;


import com.devgang.marketduck.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "user_images")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserImage extends Auditable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userImageId;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileUrl;

    @Column(nullable = false)
    private Long userId;

    public static UserImage create(String fileName, String fileUrl, Long userId) {
        return UserImage.builder()
                .fileName(fileName)
                .fileUrl(fileUrl)
                .userId(userId)
                .build();
    }
}

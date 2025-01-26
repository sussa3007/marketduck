package com.devgang.marketduck.api.user.dto;

import com.devgang.marketduck.constant.Authority;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPatchRequestDto {

    private String nickname;

    private String phoneNumber;

    private Authority authority;

    private List<Long> goodsCategory;

    private List<Long> genreCategory;

}

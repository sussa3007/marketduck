package com.devgang.marketduck.api.user.dto;

import com.devgang.marketduck.constant.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPatchRequestDto {

    private String nickname;

    private String phoneNumber;

    private Authority authority;

}

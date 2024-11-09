package com.devgang.marketduck.api.openapi.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LoginDto {

    String username;

    String password;

}
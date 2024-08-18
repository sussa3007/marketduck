package com.devgang.marketduck.openapi.user.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerifyNumRequestDto {

    private String email;

    private String verifyNum;

}

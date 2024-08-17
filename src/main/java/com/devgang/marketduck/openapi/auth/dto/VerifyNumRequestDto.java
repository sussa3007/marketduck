package com.devgang.marketduck.openapi.auth.dto;


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

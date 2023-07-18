package com.sunrint.hackaton._2023sunrinhackatonbackend.global.auth.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JWTResult {
    private String accessToken;
    private String refreshToken;

    @Override
    public String toString() {
        return "JWTResult{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}

package com.example.seodangdogbe.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JWTDto {
    private String accessToken;
    private String refreshToken;
}

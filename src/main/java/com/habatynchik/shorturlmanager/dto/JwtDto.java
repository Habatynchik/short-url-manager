package com.habatynchik.shorturlmanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtDto {
    @NotBlank
    private String accessToken;
    @NotBlank
    private String refreshToken;
}

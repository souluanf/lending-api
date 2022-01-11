package dev.luanfernandes.app.domain.dto;

import lombok.Value;

@Value
public class TokenDto {
    String token;
    String bearer;
}

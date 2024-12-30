package br.com.spring.urlshorter.dto.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class AuthUserResponse {
    private String accessToken;
    private LocalDate expirationDate;
}

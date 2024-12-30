package br.com.spring.urlshorter.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    private UUID id;
    private String username;
    private String email;
    private String password;
    private int rolesId;
    private String role;
    private LocalDateTime createdAt;
}

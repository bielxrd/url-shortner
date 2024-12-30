package br.com.spring.urlshorter.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserRequest {
    private String username;
    private String password;
}

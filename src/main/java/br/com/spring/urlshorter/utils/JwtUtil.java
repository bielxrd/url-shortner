package br.com.spring.urlshorter.utils;

import br.com.spring.urlshorter.dto.user.AuthUserResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${jwt-secret-ket}")
    private String secretKey;

    private static long EXPIRATION_TIME = 86400000;

    public AuthUserResponse generateToken(String subjectId, List<GrantedAuthority> authorities) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Instant expiresIn = Instant.now().plusMillis(EXPIRATION_TIME);

        String token = JWT.create().withIssuer("URLShorter")
                .withExpiresAt(expiresIn)
                .withSubject(subjectId)
                .withClaim("authorities", authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(algorithm);

        LocalDate expirationDate = LocalDate.ofInstant(expiresIn, ZoneId.systemDefault());

        return AuthUserResponse.builder()
                .accessToken(token)
                .expirationDate(expirationDate)
                .build();
    }

}

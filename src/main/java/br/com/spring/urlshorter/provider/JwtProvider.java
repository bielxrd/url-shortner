package br.com.spring.urlshorter.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtProvider {

    @Value("${jwt-secret-ket}")
    private String secretKey;

    public DecodedJWT decodeToken(String token) {
        token = token.replace("Bearer ", "");

        try {
            return JWT.require(Algorithm.HMAC256(secretKey))
                    .build()
                    .verify(token);
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Token inválido ou expirado");
        }
    }

    public List<String> extractRoles(DecodedJWT decoded) {
        return decoded.getClaim("authorities").asList(String.class);
    }

}

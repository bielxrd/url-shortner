package br.com.spring.urlshorter.dto.userurl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUrlRequest {
    private UUID userId;
    private String originalUrl;
    private String idUrl;
}

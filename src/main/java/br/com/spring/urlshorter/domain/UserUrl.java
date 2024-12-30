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
public class UserUrl {
    private UUID id;
    private UUID userId;
    private String originalUrl;
    private String idUrl;
    private LocalDateTime createdAt;
}

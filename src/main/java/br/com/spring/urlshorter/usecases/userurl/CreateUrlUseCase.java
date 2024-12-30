package br.com.spring.urlshorter.usecases.userurl;

import br.com.spring.urlshorter.domain.UserUrl;
import br.com.spring.urlshorter.dto.userurl.CreateUrlRequest;
import br.com.spring.urlshorter.dto.userurl.UserUrlRequest;
import br.com.spring.urlshorter.repositories.userurl.UserUrlRepository;
import br.com.spring.urlshorter.services.LambdaProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class CreateUrlUseCase {

    private final UserUrlRepository userUrlRepository;

    private final LambdaProducer lambdaProducer;

    private final Gson gson;

    private final ObjectMapper objectMapper;

    @SneakyThrows
    public UserUrl createUrlShortner(CreateUrlRequest request, UUID userId) {
        log.info("Creating short URL for user: " + userId);
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("body", gson.toJson(request));

        String payload = gson.toJson(requestBody);

        log.info("Payload: " + payload);

        String response = this.lambdaProducer.createShortUrl(payload);

        Map<String, String> responseMap;

        try {
            responseMap = objectMapper.readValue(response, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON body: " + e.getMessage(), e);
        }

        String idUrl = responseMap.get("code");

        log.info("Short URL created with code: " + idUrl);

        UserUrlRequest userUrlRequest = UserUrlRequest.builder()
                .userId(userId)
                .originalUrl(request.originalUrl())
                .idUrl(idUrl)
                .build();

        log.info("Saving short URL to database");

        return this.userUrlRepository.save(userUrlRequest);
    }

}

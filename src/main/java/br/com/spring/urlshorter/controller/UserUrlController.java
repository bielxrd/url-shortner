package br.com.spring.urlshorter.controller;

import br.com.spring.urlshorter.dto.userurl.CreateUrlRequest;
import br.com.spring.urlshorter.services.LambdaProducer;
import br.com.spring.urlshorter.usecases.userurl.CreateUrlUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/urls")
public class UserUrlController {

    private final CreateUrlUseCase createUrlUseCase;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_PREMIUM')")
    public ResponseEntity<Object> createUrl(@RequestBody CreateUrlRequest request, Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        return ResponseEntity.ok(createUrlUseCase.createUrlShortner(request, userId));
    }

}

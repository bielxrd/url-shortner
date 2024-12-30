package br.com.spring.urlshorter.controller;

import br.com.spring.urlshorter.dto.user.AuthUserRequest;
import br.com.spring.urlshorter.dto.user.AuthUserResponse;
import br.com.spring.urlshorter.dto.user.CreateUserRequest;
import br.com.spring.urlshorter.dto.user.UserResponseDTO;
import br.com.spring.urlshorter.usecases.user.AuthUserUseCase;
import br.com.spring.urlshorter.usecases.user.CreateUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final AuthUserUseCase authUserUseCase;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody CreateUserRequest request) {
        UserResponseDTO response = this.createUserUseCase.create(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthUserResponse> auth(@RequestBody AuthUserRequest request) {
        AuthUserResponse response = authUserUseCase.auth(request);
        return ResponseEntity.ok(response);
    }
}

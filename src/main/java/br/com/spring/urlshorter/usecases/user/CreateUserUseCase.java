package br.com.spring.urlshorter.usecases.user;

import br.com.spring.urlshorter.domain.User;
import br.com.spring.urlshorter.dto.user.CreateUserRequest;
import br.com.spring.urlshorter.dto.user.UserResponseDTO;
import br.com.spring.urlshorter.mapper.UserMapper;
import br.com.spring.urlshorter.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    @SneakyThrows
    public UserResponseDTO create(CreateUserRequest userRequest) {
        log.info("[UserService][create] starting to create user into database.");
        log.info("[UserService][create] validating if user email: {} or username: {} exists", userRequest.getEmail(), userRequest.getUsername());
        if (!this.userRepository.existsByEmailOrUsername(userRequest.getEmail(), userRequest.getUsername())) {
            log.info("[UserService][create] validating: {}", true);
            userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

            log.info("[UserService][create] trying to insert user into database with email: {} and username: {}", userRequest.getEmail(), userRequest.getUsername());
            User user = userRepository.save(userRequest);

            if (user != null) {
                log.info("[UserService][create] user with email: {} and username: {} inserted with successful", userRequest.getEmail(), userRequest.getUsername());
                return userMapper.mapEntityToUserResponse(user);
            }
            log.error("[UserService][create] error inserting user with email: {} and username: {}", userRequest.getEmail(), userRequest.getUsername());
            throw new RuntimeException("Usuário não foi possivel cadastrar.");
        }
        log.error("[UserService][create] user with email: {} or username: {} already exists", userRequest.getEmail(), userRequest.getUsername());
        throw new RuntimeException("Usuário já cadastrado com esse email/username.");
    }

}

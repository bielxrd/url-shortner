package br.com.spring.urlshorter.services;

import br.com.spring.urlshorter.domain.User;
import br.com.spring.urlshorter.dto.user.CustomUserDetails;
import br.com.spring.urlshorter.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
        User user = this.userRepository.findUserByUsernameOrEmail(input);

        if (user != null) {
            return new CustomUserDetails(
                    user.getId(),
                    user.getUsername(),
                    user.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
            );
        }

        throw new RuntimeException("Email/senha errados.");
    }
}

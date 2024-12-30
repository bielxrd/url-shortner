package br.com.spring.urlshorter.usecases.user;

import br.com.spring.urlshorter.dto.user.AuthUserRequest;
import br.com.spring.urlshorter.dto.user.AuthUserResponse;
import br.com.spring.urlshorter.dto.user.CustomUserDetails;
import br.com.spring.urlshorter.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class AuthUserUseCase {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthUserResponse auth(AuthUserRequest request) {
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        List<GrantedAuthority> authorities = user.getAuthorities().stream()
                .map(authority -> (GrantedAuthority) authority)
                .collect(Collectors.toList());


        return jwtUtil.generateToken(user.getId().toString(), authorities);
    }

}

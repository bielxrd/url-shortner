package br.com.spring.urlshorter.mapper;


import br.com.spring.urlshorter.domain.User;
import br.com.spring.urlshorter.dto.user.CreateUserRequest;
import br.com.spring.urlshorter.dto.user.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserResponseDTO mapEntityToUserResponse(User user) {
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt());
    }

    public User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getObject("id", UUID.class))
                .email(resultSet.getString("email"))
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .build();
    }

    public User mapResultSetToUserAuthResponse(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getObject("id", UUID.class))
                .email(resultSet.getString("email"))
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .role(resultSet.getString("role"))
                .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .build();
    }

}

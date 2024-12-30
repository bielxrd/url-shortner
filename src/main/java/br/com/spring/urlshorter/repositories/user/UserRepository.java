package br.com.spring.urlshorter.repositories.user;

import br.com.spring.urlshorter.domain.User;
import br.com.spring.urlshorter.dto.user.CreateUserRequest;
import br.com.spring.urlshorter.mapper.UserMapper;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final HikariDataSource dataSource;
    private final UserSqlQueries userQueries;
    private final UserMapper userMapper;

    public User save(CreateUserRequest request) throws Exception {
            try (Connection connection = dataSource.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(userQueries.getInsertUserQuery());

                preparedStatement.setString(1, request.getUsername());
                preparedStatement.setString(2, request.getEmail());
                preparedStatement.setString(3, request.getPassword());
                preparedStatement.setInt(4, request.getRoleId());

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return userMapper.mapResultSetToUser(resultSet);
                }
            }
            return null;
    }

    public boolean existsByEmailOrUsername(String email, String username) throws Exception {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(userQueries.getExistsByEmailOrUsername())) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean(1);
                }
            }
            return false;
        }
    }

    public User findUserByUsernameOrEmail(String input) throws Exception {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(userQueries.getFindUserByUsernameOrEmail())) {

            preparedStatement.setString(1, input);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return userMapper.mapResultSetToUserAuthResponse(resultSet);
            }

            return null;
        }
    }
}

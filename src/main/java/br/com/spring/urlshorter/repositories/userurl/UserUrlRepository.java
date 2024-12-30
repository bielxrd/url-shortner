package br.com.spring.urlshorter.repositories.userurl;

import br.com.spring.urlshorter.domain.UserUrl;
import br.com.spring.urlshorter.dto.userurl.UserUrlRequest;
import br.com.spring.urlshorter.mapper.UserUrlMapper;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
@RequiredArgsConstructor
public class UserUrlRepository {

    private final HikariDataSource dataSource;
    private final UserUrlQueries userUrlQueries;
    private final UserUrlMapper userUrlMapper;

    public UserUrl save(UserUrlRequest userUrlRequest) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(userUrlQueries.getInsertUserUrl());

            preparedStatement.setObject(1, userUrlRequest.getUserId());
            preparedStatement.setString(2, userUrlRequest.getOriginalUrl());
            preparedStatement.setString(3, userUrlRequest.getIdUrl());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return userUrlMapper.mapResultSetToUserUrl(resultSet);
            }
        }
        return null;
    }

}

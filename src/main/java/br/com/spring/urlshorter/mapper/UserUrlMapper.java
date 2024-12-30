package br.com.spring.urlshorter.mapper;

import br.com.spring.urlshorter.domain.UserUrl;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
public class UserUrlMapper {

    @SneakyThrows
    public UserUrl mapResultSetToUserUrl(ResultSet resultSet) {
        return UserUrl.builder()
                .id(resultSet.getObject("id", java.util.UUID.class))
                .originalUrl(resultSet.getString("original_url"))
                .idUrl(resultSet.getString("id_url"))
                .userId(resultSet.getObject("user_id", java.util.UUID.class))
                .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .build();
    }

}

package br.com.spring.urlshorter.repositories.userurl;

import br.com.spring.urlshorter.helpers.SqlLoader;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class UserUrlQueries {
    private final String insertUserUrl;

    public UserUrlQueries() {
        this.insertUserUrl = SqlLoader.loadSql("queries/userurl/insertUserUrl.sql");
    }

}

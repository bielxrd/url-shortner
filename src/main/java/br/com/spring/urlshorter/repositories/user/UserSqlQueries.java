package br.com.spring.urlshorter.repositories.user;

import br.com.spring.urlshorter.helpers.SqlLoader;
import org.springframework.stereotype.Component;

@Component
public class UserSqlQueries {
    private final String insertUserIntoDatabase;
    private final String existsByEmailOrUsername;
    private final String findUserByUsernameOrEmail;

    public UserSqlQueries() {
        this.insertUserIntoDatabase = SqlLoader.loadSql("queries/user/insertUser.sql");
        this.existsByEmailOrUsername = SqlLoader.loadSql("queries/user/existsByEmailOrUsername.sql");
        this.findUserByUsernameOrEmail = SqlLoader.loadSql("queries/user/findUserByUsernameOrEmail.sql");
    }

    public String getInsertUserQuery() {
        return insertUserIntoDatabase;
    }

    public String getExistsByEmailOrUsername() {
        return existsByEmailOrUsername;
    }

    public String getFindUserByUsernameOrEmail() {
        return findUserByUsernameOrEmail;
    }
}

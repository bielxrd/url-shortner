package br.com.spring.urlshorter.helpers;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class SqlLoader {

    public static String loadSql(String filePath) {
        try {
            Path path = Paths.get(Objects.requireNonNull(SqlLoader.class.getClassLoader().getResource(filePath)).toURI());
            return Files.readString(path);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load SQL file: " + filePath, e);
        }
    }

}

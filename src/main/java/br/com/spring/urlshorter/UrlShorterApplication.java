package br.com.spring.urlshorter;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

@SpringBootApplication
public class UrlShorterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlShorterApplication.class, args);
    }

}

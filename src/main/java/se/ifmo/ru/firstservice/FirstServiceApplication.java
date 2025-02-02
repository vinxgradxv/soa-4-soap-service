package se.ifmo.ru.firstservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "se.ifmo.ru.firstservice.repositories")
@EntityScan(basePackages = "se.ifmo.ru.firstservice.model")
public class FirstServiceApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FirstServiceApplication.class, args);

    }
}

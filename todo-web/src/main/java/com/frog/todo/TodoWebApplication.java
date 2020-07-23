package com.frog.todo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class TodoWebApplication {
    public static final String APPLICATION_LOCATIONS =
            "spring.config.location="
                    + "classpath:application.yml,"
                    + "classpath:./config/application-secret.yml";
    @Value("${frog.key}")
    private String key;

    public static void main(String[] args) {
        new SpringApplicationBuilder(TodoWebApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }


}

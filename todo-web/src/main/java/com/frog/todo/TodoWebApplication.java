package com.frog.todo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TodoWebApplication {
    @Value("${frog.key}")
    private String key;

    public static void main(String[] args) {
        SpringApplication.run(TodoWebApplication.class, args);
    }

    @PostConstruct
    public void foo() {
        System.out.println(key);
    }


}

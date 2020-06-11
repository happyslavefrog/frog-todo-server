package com.frog.todo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frog.todo.common.advice.filter.CreateLocationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class FilterConfiguration {

    private final ObjectMapper objectMapper;

    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new CreateLocationFilter(objectMapper));
        registrationBean.setUrlPatterns(Arrays.asList("/*"));

        return registrationBean;
    }

}
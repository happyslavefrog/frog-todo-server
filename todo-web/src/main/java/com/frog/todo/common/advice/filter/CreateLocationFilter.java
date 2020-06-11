package com.frog.todo.common.advice.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frog.todo.common.advice.ContentCachingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;


@RequiredArgsConstructor
public class CreateLocationFilter extends GenericFilterBean {

    private final ObjectMapper objectMapper;

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        ContentCachingResponse contentCachingResponse = ContentCachingResponse.of(response);

        chain.doFilter(request, contentCachingResponse);

        contentCachingResponse.setLocationHeader(objectMapper);

        contentCachingResponse.copyBodyToResponse();
    }
}

package com.frog.todo.common.advice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frog.todo.item.api.dto.StandardResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContentCachingResponse extends ContentCachingResponseWrapper {
    /**
     * Create a new ContentCachingResponseWrapper for the given servlet response.
     *
     * @param response the original servlet response
     */
    public ContentCachingResponse(final HttpServletResponse response) {
        super(response);
    }

    public static ContentCachingResponse of(final ServletResponse response) {
        if (response instanceof HttpServletResponse) {
            return new ContentCachingResponse((HttpServletResponse) response);
        }
        throw new IllegalArgumentException("HttpServletResponse 타입이 아닙니다.");
    }

    public void setLocationHeader(ObjectMapper objectMapper) throws IOException {
        if (!isCreated()) {
            return;
        }
        String location = getContent(objectMapper).getData();
        this.setHeader(HttpHeaders.LOCATION, location);

        setEmptyResponse(objectMapper);
    }

    private StandardResponseEntity<String> getContent(final ObjectMapper objectMapper) throws IOException {
        return objectMapper.readValue(this.getContentAsByteArray(), new TypeReference<StandardResponseEntity<String>>() {
        });
    }

    private void setEmptyResponse(ObjectMapper objectMapper) throws IOException {
        StandardResponseEntity<Void> entity = StandardResponseEntity.noContent();
        String response = objectMapper.writeValueAsString(entity);

        this.resetBuffer();
        this.setContentLength(response.length());
        this.getWriter().write(response, 0, response.length());
    }

    private boolean isCreated() {
        return HttpStatus.CREATED.value() == this.getStatus();
    }
}

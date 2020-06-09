package com.frog.todo.item.service.dto;

import lombok.Getter;

@Getter
public class TodoCreateRequest {
    private String contents;

    public TodoCreateRequest(final String contents) {
        this.contents = contents;
    }
}

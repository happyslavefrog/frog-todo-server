package com.frog.todo.item.service.dto;

import lombok.Getter;

@Getter
public class TodoUpdateRequest {
    private Long id;
    private String contents;

    public TodoUpdateRequest(final Long id, final String contents) {
        this.id = id;
        this.contents = contents;
    }
}

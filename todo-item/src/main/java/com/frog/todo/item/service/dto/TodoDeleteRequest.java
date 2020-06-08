package com.frog.todo.item.service.dto;

import lombok.Getter;

@Getter
public class TodoDeleteRequest {
    private Long id;

    public TodoDeleteRequest(final Long id) {
        this.id = id;
    }
}

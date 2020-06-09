package com.frog.todo.item.service.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class TodoDeleteRequest {
    @NotNull
    private Long id;

    public TodoDeleteRequest(final Long id) {
        this.id = id;
    }
}

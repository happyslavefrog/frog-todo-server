package com.frog.todo.item.service.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class TodoCreateRequest {
    @NotBlank
    private String contents;

    public TodoCreateRequest(final String contents) {
        this.contents = contents;
    }
}

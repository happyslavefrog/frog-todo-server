package com.frog.todo.item.service.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class TodoUpdateRequest {
    @NotNull
    private Long id;
    @NotBlank
    private String contents;

    public TodoUpdateRequest(final Long id, final String contents) {
        this.id = id;
        this.contents = contents;
    }
}

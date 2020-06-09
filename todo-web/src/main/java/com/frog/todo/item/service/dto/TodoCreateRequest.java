package com.frog.todo.item.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoCreateRequest {
    @NotBlank
    private String contents;

    public TodoCreateRequest(final String contents) {
        this.contents = contents;
    }
}

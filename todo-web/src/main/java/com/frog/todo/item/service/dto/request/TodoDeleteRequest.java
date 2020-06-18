package com.frog.todo.item.service.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoDeleteRequest {
    @NotNull
    private Long id;

    public TodoDeleteRequest(final Long id) {
        this.id = id;
    }
}

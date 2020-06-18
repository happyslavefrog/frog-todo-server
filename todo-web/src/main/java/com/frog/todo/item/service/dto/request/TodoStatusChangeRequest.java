package com.frog.todo.item.service.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class TodoStatusChangeRequest {
    @NotNull
    private Long id;
    @NotBlank
    private String status;

    public TodoStatusChangeRequest(final Long id, final String status) {
        this.id = id;
        this.status = status;
    }

}

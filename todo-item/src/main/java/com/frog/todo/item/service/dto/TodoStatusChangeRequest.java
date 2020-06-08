package com.frog.todo.item.service.dto;

import lombok.Getter;

@Getter
public class TodoStatusChangeRequest {
    private Long id;
    private String status;

    public TodoStatusChangeRequest(final Long id, final String status) {
        this.id = id;
        this.status = status;
    }

}

package com.frog.todo.item.service.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TodoItemsResponse {
    private List<TodoItemResponse> todoItems;

    public TodoItemsResponse(final List<TodoItemResponse> todoItems) {
        this.todoItems = todoItems;
    }

}

package com.frog.todo.item.service.dto;

import com.frog.todo.item.domain.model.TodoItem;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TodoItemResponse {
    private Long id;
    private String contents;

    public TodoItemResponse(final Long id, final String contents) {
        this.id = id;
        this.contents = contents;
    }

    public static List<TodoItemResponse> listOf(List<TodoItem> todoItems) {
        return todoItems.stream()
                .map(todoItem -> new TodoItemResponse(todoItem.getId(), todoItem.getContents()))
                .collect(Collectors.toList());
    }
}

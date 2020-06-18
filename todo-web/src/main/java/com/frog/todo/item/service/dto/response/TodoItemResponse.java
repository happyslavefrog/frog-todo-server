package com.frog.todo.item.service.dto.response;

import com.frog.todo.domain.model.TodoItem;
import com.frog.todo.domain.model.TodoStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TodoItemResponse {
    private Long id;
    private String contents;
    private TodoStatus todoStatus;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public TodoItemResponse() {
    }

    public TodoItemResponse(final Long id, final String contents, final TodoStatus todoStatus, final LocalDateTime createdTime, final LocalDateTime updatedTime) {
        this.id = id;
        this.contents = contents;
        this.todoStatus = todoStatus;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public TodoItemResponse(final Long id, final String contents) {
        this.id = id;
        this.contents = contents;
    }

    public static List<TodoItemResponse> listOf(List<TodoItem> todoItems) {
        return todoItems.stream()
                .map(todoItem -> new TodoItemResponse(todoItem.getId(), todoItem.getContents(), todoItem.getTodoStatus(), todoItem.getCreatedTime(), todoItem.getUpdatedTime()))
                .collect(Collectors.toList());
    }
}

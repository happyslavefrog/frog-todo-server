package com.frog.todo.item.api;

import com.frog.todo.item.api.dto.StandardResponseEntity;
import com.frog.todo.item.service.TodoItemService;
import com.frog.todo.item.service.dto.TodoCreateRequest;
import com.frog.todo.item.service.dto.TodoItemResponse;
import com.frog.todo.item.service.dto.TodoUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoItemController {

    private final TodoItemService todoItemService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public StandardResponseEntity<List<TodoItemResponse>> getAllTodoItems() {
        return StandardResponseEntity.ok(todoItemService.getAllTodoItems());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StandardResponseEntity<Long> createTodoItem(@RequestBody TodoCreateRequest todoCreateRequest) {
        Long id = todoItemService.save(todoCreateRequest);
        return StandardResponseEntity.ok(id);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public StandardResponseEntity<Void> updateContents(@RequestBody TodoUpdateRequest todoUpdateRequest) {
        todoItemService.update(todoUpdateRequest);
        return StandardResponseEntity.noContent();
    }
}

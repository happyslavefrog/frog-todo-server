package com.frog.todo.item.api.controller;

import com.frog.todo.item.api.dto.StandardResponseEntity;
import com.frog.todo.item.service.TodoItemService;
import com.frog.todo.item.service.dto.TodoCreateRequest;
import com.frog.todo.item.service.dto.TodoDeleteRequest;
import com.frog.todo.item.service.dto.TodoItemResponse;
import com.frog.todo.item.service.dto.TodoStatusChangeRequest;
import com.frog.todo.item.service.dto.TodoUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoItemController {

    private final TodoItemService todoItemService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public StandardResponseEntity<List<TodoItemResponse>> getAllTodoItems() {
        return StandardResponseEntity.of(todoItemService.getAllTodoItems());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StandardResponseEntity<String> createTodoItem(@RequestBody @Valid TodoCreateRequest todoCreateRequest) {
        Long id = todoItemService.save(todoCreateRequest);
        return StandardResponseEntity.of("/api/todos/" + id);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public StandardResponseEntity<Void> updateContents(@RequestBody @Valid TodoUpdateRequest todoUpdateRequest) {
        todoItemService.update(todoUpdateRequest);
        return StandardResponseEntity.noContent();
    }

    @PatchMapping("/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public StandardResponseEntity<Void> toggleTodoItem(@RequestBody @Valid TodoStatusChangeRequest todoStatusChangeRequest) {
        todoItemService.changeStatus(todoStatusChangeRequest);
        return StandardResponseEntity.noContent();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public StandardResponseEntity<Void> deleteTodoItem(@RequestBody @Valid TodoDeleteRequest todoDeleteRequest) {
        todoItemService.delete(todoDeleteRequest);
        return StandardResponseEntity.noContent();
    }
}

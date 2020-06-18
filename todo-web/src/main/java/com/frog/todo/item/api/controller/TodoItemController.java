package com.frog.todo.item.api.controller;

import com.frog.todo.item.api.dto.StandardResponseEntity;
import com.frog.todo.item.service.TodoItemService;
import com.frog.todo.item.service.dto.request.TodoCreateRequest;
import com.frog.todo.item.service.dto.request.TodoDeleteRequest;
import com.frog.todo.item.service.dto.request.TodoStatusChangeRequest;
import com.frog.todo.item.service.dto.request.TodoUpdateRequest;
import com.frog.todo.item.service.dto.response.TodoItemsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoItemController {

    private final TodoItemService todoItemService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public StandardResponseEntity<TodoItemsResponse> getAllTodoItems() {
        return StandardResponseEntity.of(new TodoItemsResponse(todoItemService.getAllTodoItems()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StandardResponseEntity<Void> createTodoItem(@RequestBody @Valid TodoCreateRequest todoCreateRequest, HttpServletResponse response) {
        Long id = todoItemService.save(todoCreateRequest);
        response.setHeader(HttpHeaders.LOCATION, "/api/todos/" + id);
        return StandardResponseEntity.noContent();
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

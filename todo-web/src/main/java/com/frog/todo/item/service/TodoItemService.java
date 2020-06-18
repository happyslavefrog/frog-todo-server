package com.frog.todo.item.service;

import com.frog.todo.domain.model.TodoItem;
import com.frog.todo.domain.model.TodoStatus;
import com.frog.todo.domain.repository.TodoItemRepository;
import com.frog.todo.item.service.dto.request.TodoCreateRequest;
import com.frog.todo.item.service.dto.request.TodoDeleteRequest;
import com.frog.todo.item.service.dto.request.TodoStatusChangeRequest;
import com.frog.todo.item.service.dto.request.TodoUpdateRequest;
import com.frog.todo.item.service.dto.response.TodoItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoItemService {

    private final TodoItemRepository todoItemRepository;

    @Transactional
    public Long save(TodoCreateRequest todoCreateRequest) {
        TodoItem todoItem = new TodoItem(todoCreateRequest.getContents());
        TodoItem persistItem = todoItemRepository.save(todoItem);
        return persistItem.getId();
    }

    @Transactional
    public void update(TodoUpdateRequest todoUpdateRequest) {
        TodoItem todoItem = findById(todoUpdateRequest.getId());
        todoItem.update(todoUpdateRequest.getContents());
    }

    @Transactional
    public void changeStatus(TodoStatusChangeRequest todoStatusChangeRequest) {
        TodoStatus todoStatus = TodoStatus.findByStatus(todoStatusChangeRequest.getStatus());
        TodoItem todoItem = findById(todoStatusChangeRequest.getId());
        todoItem.changeStatus(todoStatus);
    }

    private TodoItem findById(final Long id) {
        return todoItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("존재하지 않는 TodoItem : %d", id)));
    }

    @Transactional
    public void delete(TodoDeleteRequest todoDeleteRequest) {
        TodoItem todoItem = findById(todoDeleteRequest.getId());
        todoItemRepository.delete(todoItem);
    }

    public List<TodoItemResponse> getAllTodoItems() {
        List<TodoItem> todoItems = todoItemRepository.findAll();
        return TodoItemResponse.listOf(todoItems);
    }

}

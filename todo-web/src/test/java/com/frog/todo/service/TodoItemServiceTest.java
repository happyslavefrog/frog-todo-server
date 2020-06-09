package com.frog.todo.service;

import com.frog.todo.item.domain.model.TodoItem;
import com.frog.todo.item.domain.model.TodoStatus;
import com.frog.todo.item.domain.repository.TodoItemRepository;
import com.frog.todo.item.service.TodoItemService;
import com.frog.todo.item.service.dto.TodoCreateRequest;
import com.frog.todo.item.service.dto.TodoDeleteRequest;
import com.frog.todo.item.service.dto.TodoItemResponse;
import com.frog.todo.item.service.dto.TodoStatusChangeRequest;
import com.frog.todo.item.service.dto.TodoUpdateRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TodoItemServiceTest {
    private static final String BASIC_CONTENTS = "투두 아이템 내용";
    private static final String CHANGE_CONTENTS = "바꾼 투두 아이템 내용";

    @Autowired
    private TodoItemService todoItemService;

    @Autowired
    private TodoItemRepository todoItemRepository;

    @AfterEach
    void tearDown() {
        todoItemRepository.deleteAll();
    }

    @DisplayName("투두 아이템 저장하기")
    @Test
    void save() {
        //given
        TodoCreateRequest todoCreateRequest = new TodoCreateRequest(BASIC_CONTENTS);

        //when
        Long id = todoItemService.save(todoCreateRequest);

        //then
        TodoItem todoItem = todoItemRepository.findById(id).orElseThrow(RuntimeException::new);
        assertThat(id).isEqualTo(todoItem.getId());
        assertThat(todoItem.getContents()).isNotBlank();
    }

    @DisplayName("투두 아이템 업데이트하기")
    @Test
    void update() {
        //given
        TodoItem todoItem = new TodoItem(BASIC_CONTENTS);
        TodoItem save = todoItemRepository.save(todoItem);

        TodoUpdateRequest todoUpdateRequest = new TodoUpdateRequest(save.getId(), CHANGE_CONTENTS);

        //when
        todoItemService.update(todoUpdateRequest);

        //then
        TodoItem updatedItem = todoItemRepository.findById(save.getId()).orElseThrow(RuntimeException::new);

        assertThat(updatedItem.getContents()).isEqualTo(todoUpdateRequest.getContents());
    }

    @DisplayName("투두 아이템 제거하기")
    @Test
    void delete() {
        //given
        TodoItem todoItem = new TodoItem(BASIC_CONTENTS);
        TodoItem save = todoItemRepository.save(todoItem);

        TodoDeleteRequest todoDeleteRequest = new TodoDeleteRequest(save.getId());

        //when
        todoItemService.delete(todoDeleteRequest);

        //then
        boolean result = todoItemRepository.findById(save.getId()).isPresent();
        assertThat(result).isEqualTo(false);
    }

    @DisplayName("Todo Item의 상태값을 바꾼다")
    @Test
    void changeStatus() {
        //given
        TodoItem todoItem = todoItemRepository.save(new TodoItem(BASIC_CONTENTS));
        TodoStatusChangeRequest todoStatusChangeRequest = new TodoStatusChangeRequest(todoItem.getId(), "inProgress");

        //when
        todoItemService.changeStatus(todoStatusChangeRequest);

        //then
        TodoItem changedTodoItem = todoItemRepository.findById(todoItem.getId()).orElseThrow(RuntimeException::new);
        assertThat(changedTodoItem.getTodoStatus()).isEqualTo(TodoStatus.IN_PROGRESS);
    }

    @DisplayName("모든 Todo Items 조회")
    @Test
    void getAllTodoItems() {
        //given
        todoItemRepository.save(new TodoItem("a"));
        todoItemRepository.save(new TodoItem("a"));

        //when
        List<TodoItemResponse> allTodoItems = todoItemService.getAllTodoItems();

        //then
        assertThat(allTodoItems).hasSize(2);
    }
}
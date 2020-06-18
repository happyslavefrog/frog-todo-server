package com.frog.todo.item.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frog.todo.domain.model.TodoStatus;
import com.frog.todo.item.api.docs.TodoItemDocumentation;
import com.frog.todo.item.api.dto.StandardResponseEntity;
import com.frog.todo.item.service.TodoItemService;
import com.frog.todo.item.service.dto.request.TodoCreateRequest;
import com.frog.todo.item.service.dto.request.TodoDeleteRequest;
import com.frog.todo.item.service.dto.request.TodoStatusChangeRequest;
import com.frog.todo.item.service.dto.request.TodoUpdateRequest;
import com.frog.todo.item.service.dto.response.TodoItemResponse;
import com.frog.todo.item.service.dto.response.TodoItemsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest
class TodoItemControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private TodoItemService todoItemService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @DisplayName("문서 : 전체 투두 리스트 가져오기")
    @Test
    void getAllTodoItems() throws Exception {
        TodoItemResponse todoItemResponse = new TodoItemResponse(1L, "해야할 일", TodoStatus.TODO, LocalDateTime.now(), LocalDateTime.now());
        when(todoItemService.getAllTodoItems()).thenReturn(Arrays.asList(todoItemResponse));

        MvcResult mvcResult = this.mockMvc.perform(get("/api/todos")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(TodoItemDocumentation.getAll())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        StandardResponseEntity<TodoItemsResponse> todoItemResponseStandardResponseEntity
                = objectMapper.readValue(contentAsString, new TypeReference<StandardResponseEntity<TodoItemsResponse>>() {
        });
        TodoItemResponse data = todoItemResponseStandardResponseEntity.getData().getTodoItems().get(0);
        assertThat(data.getUpdatedTime()).isNotNull();
    }

    @DisplayName("문서 : 새로운 투두 아이템 생성")
    @Test
    void createTodoItem() throws Exception {
        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("해야할 일");
        String requestBody = objectMapper.writeValueAsString(todoCreateRequest);

        when(todoItemService.save(any(TodoCreateRequest.class))).thenReturn(1L);

        this.mockMvc.perform(post("/api/todos")
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(TodoItemDocumentation.create());
    }

    @DisplayName("문서 : 투두 아이템 내용 수정")
    @Test
    void updateContents() throws Exception {
        TodoUpdateRequest todoUpdateRequest = new TodoUpdateRequest(1L, "바꾼 해야할 일");
        String requestBody = objectMapper.writeValueAsString(todoUpdateRequest);

        doNothing().when(todoItemService).update(any(TodoUpdateRequest.class));

        this.mockMvc.perform(patch("/api/todos")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print())
                .andDo(TodoItemDocumentation.changeContents());
    }

    @DisplayName("문서 : 투두 아이템 상태 변경")
    @Test
    void toggleTodoItem() throws Exception {
        TodoStatusChangeRequest todoStatusChangeRequest = new TodoStatusChangeRequest(1L, "inProgress");
        String requestBody = objectMapper.writeValueAsString(todoStatusChangeRequest);

        doNothing().when(todoItemService).changeStatus(any(TodoStatusChangeRequest.class));

        this.mockMvc.perform(patch("/api/todos/status")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print())
                .andDo(TodoItemDocumentation.changeStatus());
    }

    @DisplayName("문서 : 투두 아이템 삭제")
    @Test
    void deleteTodoItem() throws Exception {
        TodoDeleteRequest todoDeleteRequest = new TodoDeleteRequest(1L);
        String requestBody = objectMapper.writeValueAsString(todoDeleteRequest);

        doNothing().when(todoItemService).changeStatus(any(TodoStatusChangeRequest.class));

        this.mockMvc.perform(delete("/api/todos")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print())
                .andDo(TodoItemDocumentation.delete());
    }
}
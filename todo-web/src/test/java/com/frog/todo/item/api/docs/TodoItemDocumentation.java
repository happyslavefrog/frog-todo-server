package com.frog.todo.item.api.docs;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.frog.todo.common.ApiDocumentUtils.getDocumentRequest;
import static com.frog.todo.common.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class TodoItemDocumentation {

    public static RestDocumentationResultHandler getAll() {
        return document("todos/getAll",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("code").type(JsonFieldType.NULL).description("null").optional(),
                        fieldWithPath("message").type(JsonFieldType.NULL).description("null").optional(),
                        fieldWithPath("data.todoItems[0].id").type(JsonFieldType.NUMBER).description(1),
                        fieldWithPath("data.todoItems[0].contents").type(JsonFieldType.STRING).description("해야할 일"),
                        fieldWithPath("data.todoItems[0].todoStatus").type(JsonFieldType.STRING).description("진행 상태,todo,inProgress,done"),
                        fieldWithPath("data.todoItems[0].createdTime").type(JsonFieldType.STRING).description("최초 생성 시간"),
                        fieldWithPath("data.todoItems[0].updatedTime").type(JsonFieldType.STRING).description("최종 갱신 시간")
                )
        );
    }

    public static RestDocumentationResultHandler create() {
        return document("todos/create",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        fieldWithPath("contents").type(JsonFieldType.STRING).description("해야할 일")
                ),
                responseHeaders(
                        headerWithName("Location").description("새롭게 생성된 Todo의 위치")
                ),
                responseFields(
                        fieldWithPath("code").type(JsonFieldType.NULL).description("null").optional(),
                        fieldWithPath("message").type(JsonFieldType.NULL).description("null").optional(),
                        fieldWithPath("data").type(JsonFieldType.NULL).description("null").optional()
                )
        );
    }

    public static RestDocumentationResultHandler changeContents() {
        return document("todos/changeContents",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("내용을 변경할 Todo의 번호"),
                        fieldWithPath("contents").type(JsonFieldType.STRING).description("바꾼 해야할 일")
                )
        );
    }

    public static RestDocumentationResultHandler changeStatus() {
        return document("todos/changeStatus",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("상태를 변경할 Todo의 번호"),
                        fieldWithPath("status").type(JsonFieldType.STRING).description("todo 혹은 inProgress 혹은 done")
                )
        );
    }

    public static RestDocumentationResultHandler delete() {
        return document("todos/delete",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("삭제할 Todo의 번호")
                )
        );
    }
}

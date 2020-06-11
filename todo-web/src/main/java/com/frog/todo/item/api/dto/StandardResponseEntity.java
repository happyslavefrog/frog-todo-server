package com.frog.todo.item.api.dto;

import lombok.Getter;

@Getter
public class StandardResponseEntity<T> {
    private Integer code;
    private String message;
    private T data;

    public StandardResponseEntity(final Integer code, final String message, final T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static StandardResponseEntity<Void> noContent() {
        return new StandardResponseEntity<>(null, null, null);
    }

    public static <T> StandardResponseEntity<T> of(final T data) {
        return new StandardResponseEntity<>(null, null, data);
    }

    public static StandardResponseEntity<Void> error(final Integer code, final String message) {
        return new StandardResponseEntity<>(code, message, null);
    }

    public static <T> StandardResponseEntity<T> error(final Integer code, final String message, final T data) {
        return new StandardResponseEntity<>(code, message, data);
    }

}

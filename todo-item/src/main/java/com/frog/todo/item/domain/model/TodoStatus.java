package com.frog.todo.item.domain.model;

import java.util.Arrays;

public enum TodoStatus {
    TODO("todo"),
    IN_PROGRESS("inProgress"),
    DONE("done");

    private final String status;

    TodoStatus(final String status) {
        this.status = status;
    }

    public static TodoStatus findByStatus(final String status) {
        return Arrays.stream(values())
                .filter(todoStatus -> todoStatus.equalStatus(status))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s 존재하지 않는 상태 값", status)));
    }

    private boolean equalStatus(final String status) {
        return this.status.equals(status);
    }
}

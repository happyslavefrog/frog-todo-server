package com.frog.todo.domain.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contents;
    @Enumerated
    private TodoStatus todoStatus;
    @CreatedDate
    private LocalDateTime createdTime;
    @LastModifiedDate
    private LocalDateTime updatedTime;

    public TodoItem(final String contents) {
        this.contents = contents;
        this.todoStatus = TodoStatus.TODO;
    }

    public void update(final String contents) {
        if (StringUtils.isEmpty(contents)) {
            throw new IllegalArgumentException("수정 내용이 비어있습니다.");
        }
        this.contents = contents;
    }

    public void changeStatus(final TodoStatus todoStatus) {
        this.todoStatus = todoStatus;
    }
}

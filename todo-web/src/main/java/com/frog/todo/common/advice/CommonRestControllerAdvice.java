package com.frog.todo.common.advice;

import com.frog.todo.item.api.dto.StandardResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonRestControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public StandardResponseEntity<Void> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage());
        return StandardResponseEntity.error(500, "예기치 못한 에러 발생");
    }
}

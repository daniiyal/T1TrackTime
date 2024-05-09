package com.t1school.tracktime.controller;

import com.t1school.tracktime.exception.EmptyTextException;
import com.t1school.tracktime.model.ResultMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(EmptyTextException.class)
    public ResponseEntity<ResultMessage> emptyTextException(EmptyTextException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResultMessage(exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultMessage> otherException() {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResultMessage("Произошла непредвиденная ошибка"));
    }
}

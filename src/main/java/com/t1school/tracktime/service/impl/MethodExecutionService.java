package com.t1school.tracktime.service.impl;

import com.t1school.tracktime.annotation.TrackAsyncTime;
import com.t1school.tracktime.annotation.TrackTime;
import com.t1school.tracktime.exception.EmptyTextException;
import com.t1school.tracktime.service.IMethodExecutionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class MethodExecutionService implements IMethodExecutionService {
    @Override
    @TrackTime
    public String syncMethodExecution(String text) {
        if (text == null || text.isEmpty()) {
            throw new EmptyTextException("Вызовите метод с заполненным параметром text");
        }

        try {
            Thread.sleep((long) (Math.random() * 1000));
            log.info("Выполнился синхронный метод с аргументом: {}", text);
            return "Результат синхронного метода";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    @TrackAsyncTime
    public CompletableFuture<String> asyncMethodExecution(String text) {
        if (text == null || text.isEmpty()) {
            throw new EmptyTextException("Вызовите метод с заполненным параметром text");
        }

        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep((long) (Math.random() * 1000));
                log.info("Выполнился асинхронный метод с аргументом: {}", text);
                return "Результат асинхронного метода";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

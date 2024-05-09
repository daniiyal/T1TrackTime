package com.t1school.tracktime.service;

import com.t1school.tracktime.exception.EmptyTextException;

import java.util.concurrent.CompletableFuture;

public interface IMethodExecutionService {
    /**
     * Вызов sync метода
     *
     * @param text
     * @return
     * @throws EmptyTextException
     */
    String syncMethodExecution(String text) throws EmptyTextException;

    /**
     * Вызов async метода
     *
     * @param text
     * @return
     * @throws EmptyTextException
     */
    CompletableFuture<String> asyncMethodExecution(String text) throws EmptyTextException;
}

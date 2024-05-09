package com.t1school.tracktime.service.impl;

import com.t1school.tracktime.exception.EmptyTextException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MethodExecutionServiceTest {
    @InjectMocks
    MethodExecutionService methodExecutionService;

    @Test
    public void syncMethodExecution_ValidText_ValidResult() {
        //Given
        String text = "Async";
        String expected = "Результат синхронного метода";

        //When
        String result = methodExecutionService.syncMethodExecution(text);

        //Then
        assertEquals(expected, result);
    }

    @Test
    public void syncMethodExecution_InvalidText_Exception() {
        //Given
        String expectedExceptionMessage = "Вызовите метод с заполненным параметром text";

        //When
        Exception exception = assertThrows(EmptyTextException.class, () -> methodExecutionService.syncMethodExecution(null));
        String actualExceptionMessage = exception.getMessage();

        //Then
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @Test
    public void asyncMethodExecution_ValidText_ValidResult() {
        //Given
        String text = "Async";
        String expected = "Результат асинхронного метода";

        //When
        String result = methodExecutionService.asyncMethodExecution(text).join();

        //Then
        assertEquals(expected, result);
    }

    @Test
    public void asyncMethodExecution_InvalidText_Exception() {
        //Given
        String expectedExceptionMessage = "Вызовите метод с заполненным параметром text";

        //When
        Exception exception = assertThrows(EmptyTextException.class, () -> methodExecutionService.asyncMethodExecution(null));
        String actualExceptionMessage = exception.getMessage();

        //Then
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }
}
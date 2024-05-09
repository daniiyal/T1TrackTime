package com.t1school.tracktime.controller;

import com.t1school.tracktime.dto.MethodExecutionDTO;
import com.t1school.tracktime.dto.MetricDTO;
import com.t1school.tracktime.model.ResultMessage;
import com.t1school.tracktime.service.IMethodExecutionService;
import com.t1school.tracktime.service.ITrackTimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "TimeTrack Controller", description = "TimeTrack API сервис для добавления и получения времени работы метода")
@RestController
@RequestMapping("/api/v1")
public class TimeTrackController {
    private final IMethodExecutionService methodExecutionService;

    private final ITrackTimeService trackTimeService;

    public TimeTrackController(IMethodExecutionService methodExecutionService, ITrackTimeService trackTimeService) {
        this.methodExecutionService = methodExecutionService;
        this.trackTimeService = trackTimeService;
    }

    @Operation(summary = "Вызов сихронного метода для добавления в базу",
            description = "Вызывает метод с рандомным временем выполнения и асинхронно записывает результат в базу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное выполнение"),
            @ApiResponse(responseCode = "400", description = "Аргумент вызова не прошел проверку"),
            @ApiResponse(responseCode = "500", description = "Произошла непредвиденная ошибка")}
    )
    @PostMapping("/sync")
    public ResponseEntity<ResultMessage> addSyncTime(String text) {
        String result = methodExecutionService.syncMethodExecution(text);
        return ResponseEntity.ok(new ResultMessage(result));
    }

    @Operation(summary = "Вызов асихронного метода для добавления в базу",
            description = "Вызывает метод с рандомным временем выполнения и асинхронно записывает результат в базу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное выполнение"),
            @ApiResponse(responseCode = "400", description = "Аргумент вызова не прошел проверку"),
            @ApiResponse(responseCode = "500", description = "Произошла непредвиденная ошибка")}
    )
    @PostMapping("/async")
    public ResponseEntity<ResultMessage> addAsyncTime(String text) {
        var completableFuture = methodExecutionService.asyncMethodExecution(text);
        String result = completableFuture.join();
        return ResponseEntity.ok(new ResultMessage(result));
    }


    @Operation(summary = "Получение метрик вызовов методов, разделенных по типу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное выполнение"),
            @ApiResponse(responseCode = "500", description = "Произошла непредвиденная ошибка")}
    )
    @GetMapping("metrics/type")
    public ResponseEntity<List<MetricDTO>> getTimeByType() {
        return ResponseEntity.ok(trackTimeService.getMetricsByTypes());
    }

    @Operation(summary = "Получение метрик вызовов методов, разделенных по классу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное выполнение"),
            @ApiResponse(responseCode = "500", description = "Произошла непредвиденная ошибка")}
    )
    @GetMapping("metrics/class")
    public ResponseEntity<List<MetricDTO>> getTimeByClass() {
        return ResponseEntity.ok(trackTimeService.getMetricsByClasses());
    }

    @Operation(summary = "Получение результатов всех вызовов методов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное выполнение"),
            @ApiResponse(responseCode = "500", description = "Произошла непредвиденная ошибка")}
    )
    @GetMapping("metrics/all")
    public ResponseEntity<List<MethodExecutionDTO>> getAllExecutions() {
        return ResponseEntity.ok(trackTimeService.getAllExecutions());
    }
}

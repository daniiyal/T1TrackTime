package com.t1school.tracktime.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Описание метода и список времени выполнения его вызовов")
public class MethodExecutionDTO {
    /**
     * Имя метода
     */
    @Schema(description = "Имя метода")
    private String methodName;

    /**
     * Имя класса метода
     */
    @Schema(description = "Имя класса метода")
    private String className;

    /**
     * Тип метода
     */
    @Schema(description = "Тип метода")
    private String type;

    /**
     * Список времени выполнения метода
     */
    @Schema(description = "Список времени выполнения метода")
    List<Long> executions;
}

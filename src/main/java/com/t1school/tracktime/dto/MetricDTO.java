package com.t1school.tracktime.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Метрики времени выполнения метода")
public class MetricDTO {
    /**
     * Имя типа метрики (по классу, по типу)
     */
    @Schema(description = "Имя типа метрики")
    private String entityName;

    /**
     * Общее время выполения
     */
    @Schema(description = "Общее время выполения")
    private Long totalTime;

    /**
     * Среднее время выполения
     */
    @Schema(description = "Среднее время выполения")
    private Long averageTime;

    /**
     * Минимальное время выполения
     */
    @Schema(description = "Минимальное время выполения")
    private Long minTime;

    /**
     * Максимальное время выполения
     */
    @Schema(description = "Максимальное время выполения")
    private Long maxTime;
}

package com.t1school.tracktime.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Результат выполнения вызова")
public class ResultMessage {
    /**
     * Сообщение
     */
    @Schema(description = "Сообщение")
    private String message;
}

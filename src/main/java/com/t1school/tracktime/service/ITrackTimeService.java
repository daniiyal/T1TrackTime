package com.t1school.tracktime.service;

import com.t1school.tracktime.dto.MethodExecutionDTO;
import com.t1school.tracktime.dto.MetricDTO;
import com.t1school.tracktime.enums.MethodTypeEnum;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.List;

public interface ITrackTimeService {
    /**
     * Сохранение времени выполнения метода
     *
     * @param executionTime
     * @param joinPoint
     * @param type
     */
    void saveExecution(long executionTime, ProceedingJoinPoint joinPoint, MethodTypeEnum type);

    /**
     * Получить метрики выполнения по типам (sync, async)
     *
     * @return Список объектов MetricDTO
     * @see MetricDTO
     */
    List<MetricDTO> getMetricsByTypes();

    /**
     * Получить метрики выполнения по классам
     *
     * @return Список объектов MetricDTO
     * @see MetricDTO
     */
    List<MetricDTO> getMetricsByClasses();

    /**
     * Получить все выполнения всех методов
     *
     * @return Список объектов MetricDTO
     * @see MethodExecutionDTO
     */
    List<MethodExecutionDTO> getAllExecutions();
}

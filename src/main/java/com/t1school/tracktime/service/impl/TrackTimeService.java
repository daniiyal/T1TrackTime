package com.t1school.tracktime.service.impl;

import com.t1school.tracktime.dto.MethodExecutionDTO;
import com.t1school.tracktime.dto.MetricDTO;
import com.t1school.tracktime.entity.ExecutionEntity;
import com.t1school.tracktime.entity.MethodEntity;
import com.t1school.tracktime.enums.MethodTypeEnum;
import com.t1school.tracktime.mapper.MethodExecutionMapper;
import com.t1school.tracktime.mapper.MetricMapper;
import com.t1school.tracktime.repository.ExecutionRepository;
import com.t1school.tracktime.repository.MethodRepository;
import com.t1school.tracktime.service.ITrackTimeService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class TrackTimeService implements ITrackTimeService {
    private final MethodRepository methodRepository;
    private final ExecutionRepository executionRepository;

    private final MetricMapper typeMapper;
    private final MethodExecutionMapper methodExecutionMapper;

    public TrackTimeService(MethodRepository methodRepository, ExecutionRepository executionRepository, MetricMapper typeMapper, MethodExecutionMapper methodExecutionMapper) {
        this.methodRepository = methodRepository;
        this.executionRepository = executionRepository;
        this.typeMapper = typeMapper;
        this.methodExecutionMapper = methodExecutionMapper;
    }

    @Override
    public void saveExecution(long executionTime, ProceedingJoinPoint joinPoint, MethodTypeEnum type) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        try {
            CompletableFuture.runAsync(() -> {
                Optional<MethodEntity> optionalMethod =
                        methodRepository.findByClassNameAndMethodNameAndType(className, methodName, type.getName());

                optionalMethod.ifPresentOrElse(method -> writeExecution(method, executionTime),
                        () -> writeExecution(writeMethod(className, methodName, type), executionTime));
            });
        } catch (Exception exception) {
            log.error("Не удалось сохранить время выполнения метода {}", methodName, exception);
        }
    }

    private MethodEntity writeMethod(String className, String methodName, MethodTypeEnum type) {
        try {
            MethodEntity methodEntity = new MethodEntity();

            methodEntity.setClassName(className);
            methodEntity.setMethodName(methodName);
            methodEntity.setType(type.getName());

            return methodRepository.save(methodEntity);
        } catch (Exception exception) {
            log.error("Не удалось сохранить метод {}. Ошибка: {}", methodName, exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    private void writeExecution(MethodEntity methodEntity, long executionTime) {
        ExecutionEntity executionEntity = new ExecutionEntity();

        executionEntity.setMethodEntity(methodEntity);
        executionEntity.setExecutionTime(executionTime);

        executionRepository.save(executionEntity);
    }

    public List<MetricDTO> getMetricsByTypes() {
        try {
            List<MetricDTO> metricDTOList = new ArrayList<>();

            for (MethodTypeEnum typeEnum : MethodTypeEnum.values()) {
                var methods = methodRepository.findAllByType(typeEnum.getName());
                MetricDTO typeDTO = methods.map(m -> typeMapper.methodEntityToTypeDTO(m, typeEnum.getName())).orElse(new MetricDTO());
                metricDTOList.add(typeDTO);
            }

            return metricDTOList;
        } catch (Exception exception) {
            log.error("Не удалось найти время выполнения методов по типам", exception);
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<MetricDTO> getMetricsByClasses() {
        try {
            List<MetricDTO> metricDTOList = new ArrayList<>();
            var allMethods = methodRepository.findAll();
            for (String className : allMethods.stream().map(MethodEntity::getClassName).distinct().toList()) {
                var methods = methodRepository.findAllByClassName(className);
                MetricDTO typeDTO = methods.map(m -> typeMapper.methodEntityToTypeDTO(m, className)).orElse(new MetricDTO());
                metricDTOList.add(typeDTO);
            }

            return metricDTOList;
        } catch (Exception exception) {
            log.error("Не удалось найти время выполнения методов по классам", exception);
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<MethodExecutionDTO> getAllExecutions() {
        try {
            List<MethodEntity> methodEntities = methodRepository.findAll();
            return methodEntities.stream().map(methodExecutionMapper::methodEntityToDTO).toList();
        } catch (Exception exception) {
            log.error("Не удалось найти время выполнения всех методов", exception);
            throw new RuntimeException(exception);
        }
    }
}

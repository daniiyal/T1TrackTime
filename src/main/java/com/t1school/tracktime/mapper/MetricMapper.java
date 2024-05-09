package com.t1school.tracktime.mapper;

import com.t1school.tracktime.dto.MetricDTO;
import com.t1school.tracktime.entity.ExecutionEntity;
import com.t1school.tracktime.entity.MethodEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        builder = @Builder(disableBuilder = true))
public interface MetricMapper {


    @Mapping(target = "entityName", source = "entityName")
    @Mapping(target = "totalTime", source = "methodEntities", qualifiedByName = "getTotalTime")
    @Mapping(target = "averageTime", source = "methodEntities", qualifiedByName = "getAverageTime")
    @Mapping(target = "minTime", source = "methodEntities", qualifiedByName = "getMinTime")
    @Mapping(target = "maxTime", source = "methodEntities", qualifiedByName = "getMaxTime")
    MetricDTO methodEntityToTypeDTO(List<MethodEntity> methodEntities, String entityName);

    @Named("getTotalTime")
    default Long getTotalTime(List<MethodEntity> methodEntities) {
        return methodEntities.stream().flatMap(methodEntity -> methodEntity.getExecutionEntities().stream())
                .mapToLong(ExecutionEntity::getExecutionTime).sum();
    }

    @Named("getAverageTime")
    default Double getAverageTime(List<MethodEntity> methodEntities) {
        return methodEntities.stream().flatMap(methodEntity -> methodEntity.getExecutionEntities().stream())
                .mapToLong(ExecutionEntity::getExecutionTime).average().orElse(0.0);
    }

    @Named("getMinTime")
    default Long getMinTime(List<MethodEntity> methodEntities) {
        return methodEntities.stream().flatMap(methodEntity -> methodEntity.getExecutionEntities().stream())
                .mapToLong(ExecutionEntity::getExecutionTime).min().orElse(0L);
    }

    @Named("getMaxTime")
    default Long getMaxTime(List<MethodEntity> methodEntities) {
        return methodEntities.stream().flatMap(methodEntity -> methodEntity.getExecutionEntities().stream())
                .mapToLong(ExecutionEntity::getExecutionTime).max().orElse(0L);
    }

}

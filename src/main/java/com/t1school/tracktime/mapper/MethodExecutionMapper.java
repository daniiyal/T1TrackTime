package com.t1school.tracktime.mapper;

import com.t1school.tracktime.dto.MethodExecutionDTO;
import com.t1school.tracktime.entity.ExecutionEntity;
import com.t1school.tracktime.entity.MethodEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        builder = @Builder(disableBuilder = true))
public interface MethodExecutionMapper {

    @Mapping(target = "executions", source = "methodEntity", qualifiedByName = "getExecutions")
    MethodExecutionDTO methodEntityToDTO(MethodEntity methodEntity);

    @Named("getExecutions")
    default List<Long> getExecutions(MethodEntity methodEntity) {
        return methodEntity.getExecutionEntities().stream()
                .map(ExecutionEntity::getExecutionTime).toList();
    }
}

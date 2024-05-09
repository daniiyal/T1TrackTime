package com.t1school.tracktime.repository;

import com.t1school.tracktime.entity.MethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MethodRepository extends JpaRepository<MethodEntity, Long> {
    Optional<MethodEntity> findByClassNameAndMethodNameAndType(String className, String methodName, String type);

    Optional<List<MethodEntity>> findAllByType(String type);

    Optional<List<MethodEntity>> findAllByClassName(String className);

}

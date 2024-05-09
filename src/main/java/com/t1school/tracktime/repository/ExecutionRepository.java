package com.t1school.tracktime.repository;

import com.t1school.tracktime.entity.ExecutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutionRepository extends JpaRepository<ExecutionEntity, Long> {
}

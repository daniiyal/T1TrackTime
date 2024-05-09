package com.t1school.tracktime.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ExecutionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "execution_id")
    private Long id;

    private Long executionTime;

    @ManyToOne
    @JoinColumn(name = "method_id")
    private MethodEntity methodEntity;
}

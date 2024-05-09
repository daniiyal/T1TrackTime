package com.t1school.tracktime.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class MethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "method_id")
    private Long id;

    @Column(name = "ClassName")
    private String className;

    @Column(name = "MethodName")
    private String methodName;

    @Column(name = "Type")
    private String type;

    @OneToMany(mappedBy = "methodEntity")
    List<ExecutionEntity> executionEntities = new ArrayList<>();
}

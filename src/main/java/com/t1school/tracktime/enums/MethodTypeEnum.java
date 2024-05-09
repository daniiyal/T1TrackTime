package com.t1school.tracktime.enums;

import lombok.Getter;

@Getter
public enum MethodTypeEnum {
    SYNC("sync"),
    ASYNC("async");

    final String name;

    MethodTypeEnum(String name) {
        this.name = name;
    }

    public static MethodTypeEnum findByType(String name) {
        for (MethodTypeEnum methodType : MethodTypeEnum.values()) {
            if (methodType.getName().equals(name)) {
                return methodType;
            }
        }
        return null;
    }
}

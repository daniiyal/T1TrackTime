package com.t1school.tracktime.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ExceptionHandlerAspect {
    @AfterThrowing(pointcut = "execution(* com.t1school.tracktime.service.*.*(..)) " +
            "throws @com.t1school.tracktime.annotation.Throw *", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception) {
        log.error("Произошла ошибка при вызове метода: {}", joinPoint.getSignature().toShortString());
        log.error("Ошибка: {}", exception.getMessage());
    }
}

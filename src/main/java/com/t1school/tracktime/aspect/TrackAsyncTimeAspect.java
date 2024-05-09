package com.t1school.tracktime.aspect;

import com.t1school.tracktime.enums.MethodTypeEnum;
import com.t1school.tracktime.service.ITrackTimeService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Aspect
@Slf4j
public class TrackAsyncTimeAspect {
    private final ITrackTimeService trackTimeService;

    public TrackAsyncTimeAspect(ITrackTimeService trackTimeService) {
        this.trackTimeService = trackTimeService;
    }

    @Pointcut("@annotation(com.t1school.tracktime.annotation.TrackAsyncTime)")
    public void asyncTrackTime() {
    }

    @Around("asyncTrackTime()")
    public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
        var startTime = System.currentTimeMillis();

        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        CompletableFuture<?> result = (CompletableFuture<?>) joinPoint.proceed();
        Object res = result.join();

        var endTime = System.currentTimeMillis();
        log.info("Метод {} с аргументами {}, выполнился за {} мс с результатом  \"{}\"",
                methodName, methodArgs, endTime - startTime, res);

        trackTimeService.saveExecution(endTime - startTime, joinPoint, MethodTypeEnum.SYNC);

        return result;
    }
}

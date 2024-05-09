package com.t1school.tracktime.aspect;

import com.t1school.tracktime.enums.MethodTypeEnum;
import com.t1school.tracktime.service.ITrackTimeService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class TrackTimeAspect {

    private final ITrackTimeService trackTimeService;

    public TrackTimeAspect(ITrackTimeService trackTimeService) {
        this.trackTimeService = trackTimeService;
    }

    @Pointcut("@annotation(com.t1school.tracktime.annotation.TrackTime)")
    public void trackTimePointCut() {
    }

    @Around("trackTimePointCut()")
    public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
        var startTime = System.currentTimeMillis();

        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        Object result = joinPoint.proceed();

        var endTime = System.currentTimeMillis();
        log.info("Метод {} с аргументами {}, выполнился за {} мс с результатом  \"{}\"",
                methodName, methodArgs, endTime - startTime, result);

        trackTimeService.saveExecution(endTime - startTime, joinPoint, MethodTypeEnum.SYNC);

        return result;
    }

}

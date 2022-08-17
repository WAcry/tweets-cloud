package com.ziyuan.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ServiceLogAspect {
    /**
     * This around type aspect calls when service.impl start and end to record time cost.
     */
    @Around("execution(* com.ziyuan..*.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("====== Start: {}.{} ======",
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName());

        // record start time
        long begin = System.currentTimeMillis();

        // do service
        Object result = joinPoint.proceed();

        // record end time
        long end = System.currentTimeMillis();
        long takeTime = end - begin;

        if (takeTime > 30000) { // if the service takes time more than 30s, log error
            log.error("====== End，costs：{} ms ======", takeTime);
        } else if (takeTime > 1000) { // if the service takes time more than 10s, log warn
            log.warn("====== End，costs：{} ms ======", takeTime);
        } else { // if the service takes time less than 10s, log info
            log.info("====== End，costs：{} ms ======", takeTime);
        }

        return result;
    }

}

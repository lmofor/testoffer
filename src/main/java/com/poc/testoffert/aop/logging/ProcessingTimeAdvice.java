package com.poc.testoffert.aop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Aspect
@Service
public class ProcessingTimeAdvice {

    private final Logger log = LoggerFactory.getLogger(ProcessingTimeAdvice.class);


    /**
     * Retrieves the {@link Logger} associated to the given {@link JoinPoint}.
     *
     * @param joinPoint join point we want the logger for.
     * @return {@link Logger} associated to the given {@link JoinPoint}.
     */
    private Logger logger(JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
    }

    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut("within(com.poc.testoffert.repository..*)"+
            " || within(com.poc.testoffert.service..*)"+
            " || within(com.poc.testoffert.web.rest..*)")
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Advice that logs the input and output of each call and the processing time.
     *
     * @param joinPoint join point for advice.
     * @return result.
     * @throws Throwable throws {@link IllegalArgumentException}.
     */

    @Around("applicationPackagePointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = logger(joinPoint);
        long startTime = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long endtime = System.currentTimeMillis();
            log.info("Class Name: {} . Method Name: {} with arguments : {} "
                    + ".  Result : {} . Time taken for Execution is : {} ms. "
                    , joinPoint.getSignature().getDeclaringTypeName()
                    ,joinPoint.getSignature().getName()
                    ,Arrays.toString(joinPoint.getArgs())
                    ,result.toString()
                    , endtime-startTime);
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());
            throw e;
        }

    }
}

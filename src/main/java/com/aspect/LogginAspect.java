package com.aspect;


import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
class LogginAspect {

    private Logger logger = Logger.getLogger(LogginAspect.class.getName());

    @Pointcut("within(com.service.signIn.SignInServiceImpl)")
    public void stringProcessingMethods() {
    };

    @After("stringProcessingMethods()")
    public void logMethodCall(JoinPoint jp) {
        String methodName = jp.getSignature()
                .getName();

        logger.log(Level.INFO, "название метода: " + methodName);
    }

    @AfterReturning(pointcut = "execution(public String com.service.signIn.SignInServiceImpl.signIn())", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {

        logger.log(Level.INFO, "возвращенное значение: " + result.toString());
    }

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        logger.log(Level.INFO, joinPoint.getSignature() + " выполнен за " + executionTime + "мс");
        return proceed;
    }
}
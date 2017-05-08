package com.andersen.aspectLog;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


@Component
@Aspect
public class LoggingAspect {

    @Autowired
    CustomLogger customLogger;

    private long timeBefore, timeEnd = 0L;

    private Method method;


    @Before("@annotation(com.andersen.aspectLog.Loggable)")
    public void logBefore(JoinPoint joinPoint) throws InvocationTargetException, IllegalAccessException {

        timeBefore = System.currentTimeMillis();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        method = signature.getMethod();

        Class[] paramTypes = method.getParameterTypes();

        Object[] args = new Object[paramTypes.length];

        Object[] params = joinPoint.getArgs();

        int i = 0;

        for (Object parameter : params) {
            customLogger.log("param: " + parameter, joinPoint.getTarget().getClass(), method);
            args[i] = parameter;
            i++;
        }


        Object returnedValue = method.invoke(joinPoint.getTarget(), args);

        customLogger.log("returned value: " + returnedValue.toString(), joinPoint.getTarget().getClass(), method);
    }

    @After("@annotation(com.andersen.aspectLog.Loggable)")
    public void logAfter(JoinPoint joinPoint) {
        timeEnd = System.currentTimeMillis();
        customLogger.log("method end: " + joinPoint.getSignature().getName() + ": " + (timeEnd - timeBefore) + " ms.", joinPoint.getTarget().getClass(), method);
    }

}

package com.andersen.aspectLog;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;


@Component
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Loggable {

    enum LogLevel {INFO, DEBUG, FATAL, ERROR, WARN, TRACE, ALL}

    LogLevel level() default LogLevel.INFO;

    boolean params() default true;

    boolean result() default true;

}

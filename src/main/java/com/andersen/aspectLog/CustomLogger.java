package com.andersen.aspectLog;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class CustomLogger {

    public void log(String message, Class targetClass, Method method) {

        Logger log = Logger.getLogger(targetClass);

        Loggable loggable = method.getAnnotation(Loggable.class);

        switch (loggable.level()) {
            default:
                log.info(message);
                break;
            case INFO:
                log.info(message);
                break;
            case ERROR:
                log.error(message);
                break;
            case DEBUG:
                log.debug(message);
                break;
            case FATAL:
                log.fatal(message);
                break;
            case WARN:
                log.warn(message);
                break;
            case TRACE:
                log.trace(message);
                break;
            case ALL:
                log.info(message);
                log.error(message);
                log.debug(message);
                log.fatal(message);
                log.warn(message);
                log.trace(message);
                break;
        }
    }

    @Loggable
    public String test(String s) {
        return "testReturn: " + s;
    }
}

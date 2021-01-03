package com.pluralsight.service;

import com.pluralsight.service.logger.*;
import com.zaxxer.hikari.util.UtilityElf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service
public class LoggerService {
    private ExecutorService executorService;
    private Map<Integer, LoggerInstance> loggers;
    private static Logger logger = LoggerFactory.getLogger(LoggerService.class);

    public LoggerService() {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(2);
        this.executorService = new ThreadPoolExecutor(2,2,0,TimeUnit.SECONDS,queue);
        this.loggers = new HashMap<>();
    }

    public static Integer getID() {
        return ID;
    }

    public static void setID(Integer ID) {
        LoggerService.ID = ID;
    }

    private static Integer ID=0;

    public Map.Entry<Integer, LoggerInstance> start (Integer interval, String message, LoggerType loggerType) {
        try {
            logger.info("Creating logger instance of type {}", loggerType.toString());
            Integer loggerKey = getID();
            LoggerInstance loggerInstance = LoggerServiceFactory.getLoggerInstance(message, loggerType);
            loggerInstance.start(interval);
            loggers.put(this.ID, loggerInstance);
            executorService.submit(loggers.get(loggerKey));
            setID(getID() + 1);
            return new AbstractMap.SimpleImmutableEntry(loggerKey, loggers.get(loggerKey));
        } catch (RejectedExecutionException executionException) {
            logger.error("Couldn't create logger instance due to no threads available!");
            return null;
        }
    }

    public void stop (Integer ID) {
        if (loggers.containsKey(ID)) {
            loggers.get(ID).stop();
            loggers.remove(ID);
        }
    }
}

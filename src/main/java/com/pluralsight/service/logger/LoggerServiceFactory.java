package com.pluralsight.service.logger;

import javax.lang.model.type.NullType;
import javax.validation.constraints.Null;

public class LoggerServiceFactory {
    public static LoggerInstance getLoggerInstance (String message, LoggerType loggerType) {
        switch (loggerType) {
            case INFO:
                return new InfoLoggerInstance(message);
            case DEBUG:
                return new DebugLoggerInstance(message);
            default:
                return null;
        }
    }
}

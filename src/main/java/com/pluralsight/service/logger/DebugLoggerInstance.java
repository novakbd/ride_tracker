package com.pluralsight.service.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebugLoggerInstance extends LoggerInstance{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String message;

    public DebugLoggerInstance (String message) {
        this.message = message;
    }

    @Override
    protected void printLog() {
        logger.debug("Printing DEBUG logs: {}", message);
    }
}

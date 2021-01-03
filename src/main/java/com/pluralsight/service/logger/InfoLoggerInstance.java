package com.pluralsight.service.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InfoLoggerInstance extends LoggerInstance{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String message;

    public InfoLoggerInstance (String message) {
        this.message = message;
    }

    @Override
    protected void printLog() {
        logger.info("Printing INFO logs: {}", message);
    }
}

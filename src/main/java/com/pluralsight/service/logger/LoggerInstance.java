package com.pluralsight.service.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public abstract class LoggerInstance implements LoggerInterface,Runnable {
    private Integer interval;
    private Boolean run = Boolean.FALSE;

    @Override
    public void start(Integer interval) {
        this.interval = interval;
        this.run = Boolean.TRUE;
    }

    @Override
    public void stop() {
        this.run = Boolean.FALSE;
    }

    @Override
    public void run() {
        while (this.run) {
            try {
                this.printLog();
                TimeUnit.SECONDS.sleep(this.interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void printLog () {

    }
}
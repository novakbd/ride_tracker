package com.pluralsight.controller;

import com.pluralsight.controller.util.LogSettings;
import com.pluralsight.service.LoggerService;
import com.pluralsight.service.logger.LoggerInstance;
import com.pluralsight.service.logger.LoggerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping ("log")
public class LogController {

    private static Logger logger = LoggerFactory.getLogger(LogController.class);
    @Autowired
    private LoggerService loggerService;

    @PostMapping("/info")
    public @ResponseBody
    ResponseEntity<Map.Entry<Integer, LoggerInstance> > addInfoLogger (@RequestBody LogSettings settings) {
        logger.debug("Creating a info logger instance");
        return new ResponseEntity(loggerService.start(settings.getInterval(), settings.getMessage(), LoggerType.INFO), HttpStatus.OK);
    }

    @PostMapping("/debug")
    public @ResponseBody
    ResponseEntity<Map.Entry<Integer, LoggerInstance> > addDebugLogger (@RequestBody LogSettings settings) {
        logger.debug("Creating a debug logger instance");
        Map.Entry<Integer, LoggerInstance> entry = loggerService.start(settings.getInterval(), settings.getMessage(), LoggerType.DEBUG);
        return new ResponseEntity(entry, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteLogger (@PathVariable("id") Integer id) {
        logger.debug("Stopping and deleting logger instance {}", id);
        loggerService.stop(id);
    }
}

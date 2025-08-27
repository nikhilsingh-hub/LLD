package DesignPatterns.Singleton.Logger;

import Inheritance.P;
import org.apache.http.client.utils.DateUtils;
import org.springframework.boot.logging.LogLevel;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LoggerDemo {

    
    public static void main(String[] args) {

        LoggerImpl logger = LoggerImpl.getInstance();

        String logPath = "src/main/java/DesignPatterns/Singleton/Logger/demo_application.log";
        logger.setLogFile(logPath);

        logger.log(LogLevel.INFO, "Application started successfully");
        logger.log(LogLevel.DEBUG, "Debug information: User session created");
        logger.log(LogLevel.WARN, "Warning: Database connection is slow");
        logger.log(LogLevel.ERROR, "Error: Failed to connect to external service");
        logger.log(LogLevel.TRACE, "Trace: Entering user authentication method");


        for (int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
} 
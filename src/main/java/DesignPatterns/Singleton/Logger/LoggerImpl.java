package DesignPatterns.Singleton.Logger;

import Generics.Parent;
import org.springframework.boot.logging.LogLevel;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerImpl implements Logger {

    private static volatile LoggerImpl Instance;
    private String logFilePath;
    private PrintWriter printWriter;
    private boolean isInitialized = false;

    private LoggerImpl(){}

    @Override
    public void log(LogLevel level, String message) {
        if (!isInitialized) {
            throw new IllegalStateException("Logger is not initialized. Please call setLogFile() first.");
        }
        
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            String logEntry = String.format("[%s] [%s] %s\n", timestamp, level.name(), message);
            
            printWriter.write(logEntry);
            printWriter.flush(); // Ensure immediate writing to file
        } catch (Exception e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    @Override
    public void setLogFile(String filePath) {
        try {
            // Close existing writer if any
            if (printWriter != null) {
                printWriter.close();
            }
            
            this.logFilePath = filePath;
            
           File file   = new File(this.logFilePath);
           File parentDir = file.getParentFile();
           if(parentDir != null && !parentDir.exists()){
               parentDir.mkdirs();
           }
            
            this.printWriter = new PrintWriter(new FileWriter(this.logFilePath, true), true);
            this.isInitialized = true;
            
        } catch (IOException e) {
            System.err.println("Error setting log file: " + e.getMessage());
            this.isInitialized = false;
        }
    }

    @Override
    public String getLogFile() {
        return logFilePath;
    }

    @Override
    public void flush() {
        if (printWriter != null) {
            printWriter.flush();
        }
    }

    @Override
    public void close() {
        if (printWriter != null) {
            printWriter.close();
            this.isInitialized = false;
        }
    }

    public static LoggerImpl getInstance(){
        if(Instance == null){
            synchronized(LoggerImpl.class){
                if(Instance == null){
                    Instance = new LoggerImpl();
                }
            }
        }
        return Instance;
    }

    public static void resetInstance(){
        if (Instance != null) {
            Instance.close();
            Instance = null;
        }
    }
}

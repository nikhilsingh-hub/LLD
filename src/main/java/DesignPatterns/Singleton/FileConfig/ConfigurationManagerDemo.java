package DesignPatterns.Singleton.FileConfig;

public class ConfigurationManagerDemo {
    public static void main(String[] args) {
        // Get the singleton instance
        FileBasedConfigurationManager configManager = FileBasedConfigurationManagerImpl.getInstance();
        
        // Load configuration from file
        configManager.load("src/main/java/DesignPatterns/Singleton/settings.txt");
        
        System.out.println("=== Configuration Manager Demo ===");
        
        // Test getConfiguration methods
        System.out.println("Max connections: " + configManager.getConfiguration("max.Connections"));
        System.out.println("Enable logging: " + configManager.getConfiguration("enable.Logging"));
        
        // Test type conversion
        Integer maxConnections = configManager.getConfiguration("max.Connections", Integer.class);
        Boolean enableLogging = configManager.getConfiguration("enable.Logging", Boolean.class);
        
        System.out.println("Max connections (as Integer): " + maxConnections);
        System.out.println("Enable logging (as Boolean): " + enableLogging);
        
        // Test setConfiguration methods
        configManager.setConfiguration("server.port", "8080");
        configManager.setConfiguration("debug.mode", true);
        
        System.out.println("Server port: " + configManager.getConfiguration("server.port"));
        System.out.println("Debug mode: " + configManager.getConfiguration("debug.mode"));
        
        // Test removeConfiguration
        configManager.removeConfiguration("server.port");
        System.out.println("Server port after removal: " + configManager.getConfiguration("server.port"));
        
        // Test singleton behavior
        FileBasedConfigurationManager anotherInstance = FileBasedConfigurationManagerImpl.getInstance();
        System.out.println("Same instance? " + (configManager == anotherInstance));
        
        // Test resetInstance
        FileBasedConfigurationManagerImpl.resetInstance();
        FileBasedConfigurationManager newInstance = FileBasedConfigurationManagerImpl.getInstance();
        System.out.println("New instance after reset? " + (configManager != newInstance));
        
        // Test clear
        configManager.clear();
        System.out.println("Config cleared. Max connections: " + configManager.getConfiguration("max.Connections"));
    }
} 
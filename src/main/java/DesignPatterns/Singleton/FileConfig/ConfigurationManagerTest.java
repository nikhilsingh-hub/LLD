package DesignPatterns.Singleton.FileConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigurationManagerTest {
    
    public static void main(String[] args) {
        System.out.println("=== Configuration Manager Test Suite ===");
        
        // Test 1: Singleton Pattern
        testSingletonPattern();
        
        // Test 2: Configuration Loading
        testConfigurationLoading();
        
        // Test 3: Type Conversion
        testTypeConversion();
        
        // Test 4: Configuration Management
        testConfigurationManagement();
        
        // Test 5: Reset Functionality
        testResetFunctionality();
        
        System.out.println("\n=== All Tests Completed Successfully ===");
    }
    
    private static void testSingletonPattern() {
        System.out.println("\n--- Test 1: Singleton Pattern ---");
        
        FileBasedConfigurationManager instance1 = FileBasedConfigurationManagerImpl.getInstance();
        FileBasedConfigurationManager instance2 = FileBasedConfigurationManagerImpl.getInstance();
        
        if (instance1 == instance2) {
            System.out.println("✓ Singleton pattern working: Same instance returned");
        } else {
            System.out.println("✗ Singleton pattern failed: Different instances returned");
        }
    }
    
    private static void testConfigurationLoading() {
        System.out.println("\n--- Test 2: Configuration Loading ---");
        
        try {
            // Create a temporary config file
            File tempFile = File.createTempFile("test_config", ".properties");
            FileWriter writer = new FileWriter(tempFile);
            writer.write("app.name=TestApp\n");
            writer.write("app.version=1.0\n");
            writer.write("max.threads=50\n");
            writer.close();
            
            FileBasedConfigurationManager config = FileBasedConfigurationManagerImpl.getInstance();
            config.load(tempFile.getAbsolutePath());
            
            String appName = config.getConfiguration("app.name");
            String appVersion = config.getConfiguration("app.version");
            String maxThreads = config.getConfiguration("max.threads");
            
            if ("TestApp".equals(appName) && "1.0".equals(appVersion) && "50".equals(maxThreads)) {
                System.out.println("✓ Configuration loading working: All values loaded correctly");
            } else {
                System.out.println("✗ Configuration loading failed: Values not loaded correctly");
            }
            
            // Clean up
            tempFile.delete();
            
        } catch (IOException e) {
            System.out.println("✗ Configuration loading failed: " + e.getMessage());
        }
    }
    
    private static void testTypeConversion() {
        System.out.println("\n--- Test 3: Type Conversion ---");
        
        FileBasedConfigurationManager config = FileBasedConfigurationManagerImpl.getInstance();
        
        // Test Integer conversion
        config.setConfiguration("test.int", "42");
        Integer intValue = config.getConfiguration("test.int", Integer.class);
        
        // Test Boolean conversion
        config.setConfiguration("test.boolean", "true");
        Boolean boolValue = config.getConfiguration("test.boolean", Boolean.class);
        
        // Test Double conversion
        config.setConfiguration("test.double", "3.14");
        Double doubleValue = config.getConfiguration("test.double", Double.class);
        
        if (intValue == 42 && boolValue == true && doubleValue == 3.14) {
            System.out.println("✓ Type conversion working: All types converted correctly");
        } else {
            System.out.println("✗ Type conversion failed: Some types not converted correctly");
        }
    }
    
    private static void testConfigurationManagement() {
        System.out.println("\n--- Test 4: Configuration Management ---");
        
        FileBasedConfigurationManager config = FileBasedConfigurationManagerImpl.getInstance();
        
        // Test setConfiguration
        config.setConfiguration("test.key", "test.value");
        String value = config.getConfiguration("test.key");
        
        if ("test.value".equals(value)) {
            System.out.println("✓ Set configuration working: Value set correctly");
        } else {
            System.out.println("✗ Set configuration failed: Value not set correctly");
        }
        
        // Test removeConfiguration
        config.removeConfiguration("test.key");
        String removedValue = config.getConfiguration("test.key");
        
        if (removedValue == null) {
            System.out.println("✓ Remove configuration working: Value removed correctly");
        } else {
            System.out.println("✗ Remove configuration failed: Value not removed");
        }
        
        // Test clear
        config.setConfiguration("key1", "value1");
        config.setConfiguration("key2", "value2");
        config.clear();
        
        if (config.getConfiguration("key1") == null && config.getConfiguration("key2") == null) {
            System.out.println("✓ Clear configuration working: All values cleared");
        } else {
            System.out.println("✗ Clear configuration failed: Some values not cleared");
        }
    }
    
    private static void testResetFunctionality() {
        System.out.println("\n--- Test 5: Reset Functionality ---");
        
        FileBasedConfigurationManager instance1 = FileBasedConfigurationManagerImpl.getInstance();
        
        // Reset the instance
        FileBasedConfigurationManagerImpl.resetInstance();
        
        FileBasedConfigurationManager instance2 = FileBasedConfigurationManagerImpl.getInstance();
        
        if (instance1 != instance2) {
            System.out.println("✓ Reset functionality working: New instance created after reset");
        } else {
            System.out.println("✗ Reset functionality failed: Same instance returned after reset");
        }
    }
} 
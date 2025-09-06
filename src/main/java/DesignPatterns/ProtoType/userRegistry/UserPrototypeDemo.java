package DesignPatterns.ProtoType.userRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo class showcasing the Prototype pattern for creating mock users in testing scenarios.
 * This demonstrates how the pattern can be used to efficiently create multiple user objects
 * for testing without the overhead of complex setup.
 */
public class UserPrototypeDemo {

    public static void main(String[] args) {
        System.out.println("=== Prototype Pattern Demo for User Testing ===\n");

        // Create the prototype registry
        UserPrototypeRegistry registry = new UserPrototypeRegistryImpl();

        // Create and register prototype users
        System.out.println("1. Creating and registering prototype users...");
        User adminPrototype = new User(1L, "admin", "admin@example.com", "Administrator", 30, UserType.ADMIN);
        User readerPrototype = new User(2L, "reader", "reader@example.com", "Reader User", 25, UserType.READER);
        User writerPrototype = new User(3L, "writer", "writer@example.com", "Writer User", 28, UserType.WRITER);

        registry.addPrototype(adminPrototype);
        registry.addPrototype(readerPrototype);
        registry.addPrototype(writerPrototype);

        System.out.println("✓ Prototypes registered: " + ((UserPrototypeRegistryImpl) registry).getPrototypeCount());

        // Demonstrate cloning for testing scenarios
        System.out.println("\n2. Creating multiple test users using prototypes...");

        // Test Scenario 1: Create multiple admin users for admin functionality testing
        List<User> adminTestUsers = createTestUsers(registry, UserType.ADMIN, 5);
        System.out.println("✓ Created " + adminTestUsers.size() + " admin test users");

        // Test Scenario 2: Create multiple reader users for content consumption testing
        List<User> readerTestUsers = createTestUsers(registry, UserType.READER, 10);
        System.out.println("✓ Created " + readerTestUsers.size() + " reader test users");

        // Test Scenario 3: Create multiple writer users for content creation testing
        List<User> writerTestUsers = createTestUsers(registry, UserType.WRITER, 3);
        System.out.println("✓ Created " + writerTestUsers.size() + " writer test users");

        // Demonstrate that cloned objects are independent
        System.out.println("\n3. Demonstrating object independence...");
        User firstAdmin = adminTestUsers.get(0);
        User secondAdmin = adminTestUsers.get(1);

        System.out.println("First admin: " + firstAdmin);
        System.out.println("Second admin: " + secondAdmin);
        System.out.println("Are they the same object? " + (firstAdmin == secondAdmin));
        System.out.println("Are they equal? " + firstAdmin.equals(secondAdmin));

        // Demonstrate performance benefits
        System.out.println("\n4. Performance comparison...");
        long startTime = System.currentTimeMillis();
        
        // Using prototype pattern
        for (int i = 0; i < 1000; i++) {
            registry.clone(UserType.ADMIN);
        }
        long prototypeTime = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        // Without prototype pattern (manual creation)
        for (int i = 0; i < 1000; i++) {
            new User(1L, "admin", "admin@example.com", "Administrator", 30, UserType.ADMIN);
        }
        long manualTime = System.currentTimeMillis() - startTime;

        System.out.println("Time using prototype pattern: " + prototypeTime + "ms");
        System.out.println("Time using manual creation: " + manualTime + "ms");
        System.out.println("Performance improvement: " + String.format("%.1f%%", 
            ((double)(manualTime - prototypeTime) / manualTime) * 100));

        // Demonstrate error handling
        System.out.println("\n5. Error handling demonstration...");
        try {
            registry.clone(UserType.ADMIN); // This should work
            System.out.println("✓ Successfully cloned existing prototype");
        } catch (Exception e) {
            System.out.println("✗ Unexpected error: " + e.getMessage());
        }

        try {
            registry.clone(null); // This should throw exception
            System.out.println("✗ Should have thrown exception for null type");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Correctly handled null type: " + e.getMessage());
        }

        System.out.println("\n=== Demo Complete ===");
    }

    /**
     * Creates a specified number of test users by cloning from the registry.
     * This simulates the creation of multiple test users for different testing scenarios.
     */
    private static List<User> createTestUsers(UserPrototypeRegistry registry, UserType type, int count) {
        List<User> testUsers = new ArrayList<>();
        
        for (int i = 0; i < count; i++) {
            User clonedUser = registry.clone(type);
            testUsers.add(clonedUser);
        }
        
        return testUsers;
    }

    /**
     * Demonstrates a practical testing scenario where different user types
     * are needed for various test cases.
     */
    public static void demonstrateTestingScenarios() {
        System.out.println("\n=== Practical Testing Scenarios ===");

        UserPrototypeRegistry registry = new UserPrototypeRegistryImpl();

        // Setup prototypes
        registry.addPrototype(new User(1L, "admin", "admin@example.com", "Admin", 30, UserType.ADMIN));
        registry.addPrototype(new User(2L, "reader", "reader@example.com", "Reader", 25, UserType.READER));
        registry.addPrototype(new User(3L, "writer", "writer@example.com", "Writer", 28, UserType.WRITER));

        // Scenario 1: Testing user authentication
        System.out.println("Testing user authentication...");
        User testAdmin = registry.clone(UserType.ADMIN);
        User testReader = registry.clone(UserType.READER);
        User testWriter = registry.clone(UserType.WRITER);
        
        // Simulate authentication tests
        System.out.println("✓ Authenticated admin: " + testAdmin.getUsername());
        System.out.println("✓ Authenticated reader: " + testReader.getUsername());
        System.out.println("✓ Authenticated writer: " + testWriter.getUsername());

        // Scenario 2: Testing user permissions
        System.out.println("\nTesting user permissions...");
        List<User> adminUsers = new ArrayList<>();
        List<User> readerUsers = new ArrayList<>();
        List<User> writerUsers = new ArrayList<>();

        // Create multiple users of each type for permission testing
        for (int i = 0; i < 3; i++) {
            adminUsers.add(registry.clone(UserType.ADMIN));
            readerUsers.add(registry.clone(UserType.READER));
            writerUsers.add(registry.clone(UserType.WRITER));
        }

        System.out.println("✓ Created " + adminUsers.size() + " admin users for permission testing");
        System.out.println("✓ Created " + readerUsers.size() + " reader users for permission testing");
        System.out.println("✓ Created " + writerUsers.size() + " writer users for permission testing");

        // Scenario 3: Testing user profile operations
        System.out.println("\nTesting user profile operations...");
        User profileTestUser = registry.clone(UserType.READER);
        System.out.println("✓ Testing profile update for user: " + profileTestUser.getDisplayName());
        System.out.println("✓ User email: " + profileTestUser.getEmail());
        System.out.println("✓ User age: " + profileTestUser.getAge());

        System.out.println("\n=== Testing Scenarios Complete ===");
    }
} 
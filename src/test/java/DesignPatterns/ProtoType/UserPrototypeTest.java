package DesignPatterns.ProtoType;

import DesignPatterns.ProtoType.userRegistry.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserPrototypeTest {

    private UserPrototypeRegistry registry;
    private User adminUser;
    private User readerUser;
    private User writerUser;

    @BeforeEach
    void setUp() {
        registry = new UserPrototypeRegistryImpl();
        
        // Create prototype users
        adminUser = new User(1L, "admin", "admin@example.com", "Administrator", 30, UserType.ADMIN);
        readerUser = new User(2L, "reader", "reader@example.com", "Reader User", 25, UserType.READER);
        writerUser = new User(3L, "writer", "writer@example.com", "Writer User", 28, UserType.WRITER);
    }

    @Test
    void testUserImplementsObjectClonable() {
        // Verify that User implements ObjectClonable interface
        assertTrue(adminUser instanceof ClonableObject<?>);
    }

    @Test
    void testUserCloneCreatesNewObject() {
        // Test that cloning creates a new object with same values
        User clonedUser = adminUser.cloneObject();
        
        // Verify it's a different object in memory
        assertNotSame(adminUser, clonedUser);
        
        // Verify all attributes are the same
        assertEquals(adminUser.getUserId(), clonedUser.getUserId());
        assertEquals(adminUser.getUsername(), clonedUser.getUsername());
        assertEquals(adminUser.getEmail(), clonedUser.getEmail());
        assertEquals(adminUser.getDisplayName(), clonedUser.getDisplayName());
        assertEquals(adminUser.getAge(), clonedUser.getAge());
        assertEquals(adminUser.getType(), clonedUser.getType());
    }

    @Test
    void testUserCloneIsDeepCopy() {
        // Test that cloned object is independent of original
        User original = new User(1L, "test", "test@example.com", "Test User", 25, UserType.READER);
        User cloned = original.cloneObject();

        assertNotSame(original, cloned);
    }

    @Test
    void testAddPrototype() {
        // Test adding prototypes to registry
        registry.addPrototype(adminUser);
        registry.addPrototype(readerUser);
        
        assertEquals(2, ((UserPrototypeRegistryImpl) registry).getPrototypeCount());
        assertTrue(((UserPrototypeRegistryImpl) registry).hasPrototype(UserType.ADMIN));
        assertTrue(((UserPrototypeRegistryImpl) registry).hasPrototype(UserType.READER));
    }

    @Test
    void testAddPrototypeWithNullUser() {
        // Test that adding null user throws exception
        assertThrows(IllegalArgumentException.class, () -> {
            registry.addPrototype(null);
        });
    }

    @Test
    void testGetPrototype() {
        // Test retrieving prototypes
        registry.addPrototype(adminUser);
        registry.addPrototype(readerUser);
        
        User retrievedAdmin = registry.getPrototype(UserType.ADMIN);
        User retrievedReader = registry.getPrototype(UserType.READER);
        
        assertEquals(adminUser, retrievedAdmin);
        assertEquals(readerUser, retrievedReader);
        assertNull(registry.getPrototype(UserType.WRITER));
    }

    @Test
    void testGetPrototypeWithNullType() {
        // Test that getting prototype with null type throws exception
        assertThrows(IllegalArgumentException.class, () -> {
            registry.getPrototype(null);
        });
    }

    @Test
    void testCloneFromRegistry() {
        // Test cloning from registry
        registry.addPrototype(adminUser);
        registry.addPrototype(readerUser);
        
        User clonedAdmin = registry.clone(UserType.ADMIN);
        User clonedReader = registry.clone(UserType.READER);
        
        // Verify cloned objects are different from prototypes
        assertNotSame(adminUser, clonedAdmin);
        assertNotSame(readerUser, clonedReader);
        
        // Verify cloned objects have same values as prototypes
        assertEquals(adminUser, clonedAdmin);
        assertEquals(readerUser, clonedReader);
    }

    @Test
    void testCloneFromRegistryWithNonExistentType() {
        // Test cloning non-existent type throws exception
        assertThrows(IllegalArgumentException.class, () -> {
            registry.clone(UserType.WRITER);
        });
    }

    @Test
    void testCloneFromRegistryWithNullType() {
        // Test that cloning with null type throws exception
        assertThrows(IllegalArgumentException.class, () -> {
            registry.clone(null);
        });
    }

    @Test
    void testMultipleClonesAreIndependent() {
        // Test that multiple clones are independent of each other
        registry.addPrototype(adminUser);
        
        User clone1 = registry.clone(UserType.ADMIN);
        User clone2 = registry.clone(UserType.ADMIN);
        User clone3 = registry.clone(UserType.ADMIN);
        
        // All clones should be different objects
        assertNotSame(clone1, clone2);
        assertNotSame(clone1, clone3);
        assertNotSame(clone2, clone3);
        
        // All clones should be equal in value
        assertEquals(clone1, clone2);
        assertEquals(clone1, clone3);
        assertEquals(clone2, clone3);
    }

    @Test
    void testUserEquality() {
        // Test user equality
        User user1 = new User(1L, "test", "test@example.com", "Test User", 25, UserType.READER);
        User user2 = new User(1L, "test", "test@example.com", "Test User", 25, UserType.READER);
        User user3 = new User(2L, "test", "test@example.com", "Test User", 25, UserType.READER);
        
        assertEquals(user1, user2);
        assertNotEquals(user1, user3);
    }

    @Test
    void testUserHashCode() {
        // Test user hashCode consistency
        User user1 = new User(1L, "test", "test@example.com", "Test User", 25, UserType.READER);
        User user2 = new User(1L, "test", "test@example.com", "Test User", 25, UserType.READER);
        
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void testUserToString() {
        // Test user toString method
        User user = new User(1L, "test", "test@example.com", "Test User", 25, UserType.READER);
        String expected = "User{userId=1, username='test', email='test@example.com', displayName='Test User', age=25, type=READER}";
        assertEquals(expected, user.toString());
    }

    @Test
    void testRegistryUtilityMethods() {
        // Test utility methods
        UserPrototypeRegistryImpl impl = (UserPrototypeRegistryImpl) registry;
        
        assertEquals(0, impl.getPrototypeCount());
        assertFalse(impl.hasPrototype(UserType.ADMIN));
        
        impl.addPrototype(adminUser);
        assertEquals(1, impl.getPrototypeCount());
        assertTrue(impl.hasPrototype(UserType.ADMIN));
        
        impl.clearPrototypes();
        assertEquals(0, impl.getPrototypeCount());
        assertFalse(impl.hasPrototype(UserType.ADMIN));
    }
} 
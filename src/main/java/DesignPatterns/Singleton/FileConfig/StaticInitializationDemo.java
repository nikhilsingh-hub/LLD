package DesignPatterns.Singleton.FileConfig;

// ============= BILL PUGH SINGLETON ANALYSIS =============

class BillPughSingleton {

    // Constructor is private - prevents external instantiation
    private BillPughSingleton() {
        System.out.println("BillPughSingleton constructor called!");
    }

    private class SingletonHelper {
        private static BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    // This method triggers the loading of SingletonHelper class
    public static BillPughSingleton getInstance() {
        System.out.println("getInstance() called - about to access SingletonHelper.INSTANCE");
        return SingletonHelper.INSTANCE;  // First access loads SingletonHelper class
    }
}


public class StaticInitializationDemo {
    public static void main(String[] args) {

        System.out.println("BillPughSingleton class name: " + BillPughSingleton.class.getName());

        System.out.println("\nNow calling getInstance() for the first time...");
        BillPughSingleton instance1 = BillPughSingleton.getInstance();

        System.out.println("Calling getInstance() for the second time...");
        BillPughSingleton instance2 = BillPughSingleton.getInstance();

        System.out.println("Same instance? " + (instance1 == instance2));

    }

}

// ============= TEST SINGLETON FOR DETAILED ANALYSIS =============

class TestSingleton {
    static {
        System.out.println("  -> TestSingleton outer class static block executed");
    }

    private TestSingleton() {
        System.out.println("  -> TestSingleton constructor called");
    }

    private static class Helper {
        static {
            System.out.println("  -> TestSingleton.Helper static block executed");
        }

        private static final TestSingleton INSTANCE = new TestSingleton();
    }

    public static TestSingleton getInstance() {
        System.out.println("  -> getInstance() method called");
        return Helper.INSTANCE;
    }
}

// ============= KEY INSIGHTS EXPLANATION =============

class StaticInitializationInsights {
    /*
     * ANSWERING YOUR QUESTION:
     *
     * Q: Does making private as static only means this has to be initialised when class loads?
     *
     * A: NOT EXACTLY! Here's what really happens:
     *
     * 1. STATIC FIELDS are initialized when the CLASS CONTAINING THEM is first loaded
     *
     * 2. In Bill Pugh pattern:
     *    - BillPughSingleton class loads → outer class static stuff runs
     *    - SingletonHelper class loads → INSTANCE field gets initialized
     *    - These are TWO SEPARATE class loading events!
     *
     * 3. TIMING:
     *    - BillPughSingleton loads: When first referenced (class name, static method, etc.)
     *    - SingletonHelper loads: When INSTANCE field is first accessed
     *
     * 4. THE MAGIC:
     *    - Accessing BillPughSingleton.getInstance() method doesn't load SingletonHelper
     *    - Only when return SingletonHelper.INSTANCE executes, SingletonHelper loads
     *    - This gives us LAZY initialization with THREAD SAFETY
     *
     * 5. THREAD SAFETY:
     *    - JVM guarantees class loading is thread-safe
     *    - Static field initialization is atomic and thread-safe
     *    - No need for synchronization!
     *
     * COMPARISON:
     *
     * Eager Singleton:
     * - INSTANCE created when outer class loads
     * - Happens early, may waste memory if never used
     *
     * Bill Pugh Singleton:
     * - INSTANCE created when inner class (SingletonHelper) loads
     * - Inner class only loads when INSTANCE is first accessed
     * - Perfect lazy initialization!
     *
     * Regular Lazy Singleton:
     * - INSTANCE created on first getInstance() call
     * - Needs synchronization for thread safety
     * - Performance overhead due to synchronization
     */
}

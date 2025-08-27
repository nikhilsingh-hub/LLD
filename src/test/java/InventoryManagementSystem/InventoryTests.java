package InventoryManagementSystem;

import InventoryManagementSystem.Entities.Book;
import InventoryManagementSystem.Entities.Cloth;
import InventoryManagementSystem.Entities.Electronics;
import InventoryManagementSystem.Entities.Item;
import InventoryManagementSystem.Processors.OrderProcessor;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTests {

    private List<Item> buildInventory() {
        List<Item> inventory = new ArrayList<>();

        Book book1 = new Book("1", "Clean Code", 45.99, 10, "Robert C. Martin");
        Book book2 = new Book("2", "Effective Java", 55.49, 8, "Joshua Bloch");
        Book book3 = new Book("3", "Design Patterns", 60.00, 5, "Erich Gamma");

        Cloth cloth1 = new Cloth("4", "Cotton T-Shirt", 9.99, 50, 42);
        Cloth cloth2 = new Cloth("5", "Jeans", 29.99, 30, 34);
        Cloth cloth3 = new Cloth("6", "Jacket", 79.99, 12, 40);

        Electronics e1 = new Electronics("7", "Wireless Headphones", 59.99, 15, 24);
        Electronics e2 = new Electronics("8", "Smartphone", 699.00, 7, 12);
        Electronics e3 = new Electronics("9", "Laptop", 1099.00, 4, 24);

        Shoe shoe1 = new Shoe("10", "Running Shoe", 89.99, 20, 42);
        Shoe shoe2 = new Shoe("11", "Casual Sneaker", 69.99, 25, 41);
        Shoe shoe3 = new Shoe("12", "Formal Oxford", 99.99, 10, 43);

        Collections.addAll(inventory,
                book1, book2, book3,
                cloth1, cloth2, cloth3,
                e1, e2, e3,
                shoe1, shoe2, shoe3);

        return inventory;
    }

    @Test
    void createsThreeOfEachType() {
        List<Item> inv = buildInventory();
        assertEquals(12, inv.size());
        assertEquals(3L, inv.stream().filter(i -> i instanceof Book).count());
        assertEquals(3L, inv.stream().filter(i -> i instanceof Cloth).count());
        assertEquals(3L, inv.stream().filter(i -> i instanceof Electronics).count());
        assertEquals(3L, inv.stream().filter(i -> i instanceof Shoe).count());
    }

    @Test
    void sortsByNameUsingComparable() {
        List<Item> inv = buildInventory();
        Collections.shuffle(inv, new Random(0));

        // Natural ordering by name via Item.compareTo
        Collections.sort(inv);

        List<String> names = inv.stream().map(Item::getName).collect(Collectors.toList());
        List<String> expected = new ArrayList<>(names);
        expected.sort(Comparator.naturalOrder());

        assertEquals(expected, names);
    }

    @Test
    void compareToZeroForSameNameAcrossTypes() {
        Item a = new Book("101", "Alpha", 10.0, 1, "Auth");
        Item b = new Cloth("102", "Alpha", 12.0, 2, 40);
        assertEquals(0, a.compareTo(b));
    }

    @Test
    void testOrderProcessor(){
        Random random = new Random(0);
        OrderProcessor<Order> orderOrderProcessor = new OrderProcessor<>();
        for(int i = 1; i<=10; i++){
            int val = random.nextInt();
            if(val%2==0) orderOrderProcessor.addOrder(new Order(String.valueOf(i), true));
            else orderOrderProcessor.addOrder(new Order(String.valueOf(i), false));
        }
        boolean seenRegular = false;

        while (true) {
            Order order = orderOrderProcessor.processOrder();
            if (order == null) break; // queue empty

            // Assert that express orders always come before regular ones
            assertTrue(!order.isExpress() || !seenRegular,
                    "Express order came after regular order: " + order);

            if (!order.isExpress()) {
                seenRegular = true;
            }
        }

    }
}


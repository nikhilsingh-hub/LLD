package InventoryManagementSystem;

import InventoryManagementSystem.Entities.Book;
import InventoryManagementSystem.Entities.Cloth;
import InventoryManagementSystem.Entities.Electronics;
import InventoryManagementSystem.Entities.Item;
import InventoryManagementSystem.Processors.OrderProcessor;

import java.util.*;

public class Client {
    public static void init (List<Item> inventory){
        // Initialize dummy items: 3 of each type
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

        inventory.add(book1);
        inventory.add(book2);
        inventory.add(book3);

        inventory.add(cloth1);
        inventory.add(cloth2);
        inventory.add(cloth3);

        inventory.add(e1);
        inventory.add(e2);
        inventory.add(e3);

        inventory.add(shoe1);
        inventory.add(shoe2);
        inventory.add(shoe3);
    }
    public static void main(String[] args) {
        List<Item> inventory = new ArrayList<>();
        init(inventory);

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

            if (!order.isExpress()) {
                seenRegular = true;
            }
            System.out.println(order.isExpress());


        }

//        for (Item item : inventory) {
//            System.out.println(item.getName() + " | id=" + item.getId() + " | price=" + item.getPrice() + " | qty=" + item.getQuantity());
//        }

//        Consumer<String> printer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println("Hi");
//            }
//        };
//
//        Consumer<String> printInt = new Consumer<String>() {
//            @Override
//            public void accept(String integer) {
//                System.out.println(integer);
//            }
//        };
//        Consumer<String> combined = printer.andThen(printInt);
//        combined.accept("No");


    }
}

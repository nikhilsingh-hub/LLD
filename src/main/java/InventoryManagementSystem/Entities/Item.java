package InventoryManagementSystem.Entities;

import lombok.Data;

@Data
public class Item implements Comparable<Item>{
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item(String id, String name, double price, int quantity){
        this.name = name;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }
    @Override
    public int compareTo(Item o){
        return this.name.compareTo(o.name);
    }
}

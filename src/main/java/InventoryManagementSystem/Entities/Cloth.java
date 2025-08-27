package InventoryManagementSystem.Entities;

import lombok.Data;

@Data
public class Cloth extends Item {
    private int size;

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public Cloth(String id, String name, double price, int quantity, int size){
        super(id, name, price, quantity);
        this.size = size;
    }
}

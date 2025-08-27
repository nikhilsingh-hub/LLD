package InventoryManagementSystem;

import InventoryManagementSystem.Entities.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Shoe extends Item {
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    Shoe(String id, String name, double price, int quantity, int size) {
        super(id, name, price, quantity);
        this.size = size;
    }
}


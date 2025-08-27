package InventoryManagementSystem.Entities;

import lombok.Data;

@Data
public class Electronics extends Item {
    private int warranty;

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public int getWarranty() {
        return warranty;
    }

    public Electronics(String  id, String name, double price, int quantity, int warranty){
        super(id, name, price, quantity);
        this.warranty = warranty;
    }
}

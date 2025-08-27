package InventoryManagementSystem;

import InventoryManagementSystem.Entities.Item;
import InventoryManagementSystem.Exceptions.DuplicateItemException;
import InventoryManagementSystem.Exceptions.InvalidItemException;

import java.util.*;

public class Inventory<T extends Item> {
    private HashMap<String , T> items;

    public Inventory(){
        this.items = new HashMap<>();
    }

    public void add(T item){
        if(item.getQuantity()<0) throw new InvalidItemException("-ve Quantity for id {}", String.valueOf( item.getId()));

        if(items.containsKey(item.getId())) throw new DuplicateItemException(item.getId());

        items.put(item.getId(), item);
    }

    public void remove(T item){
        if(!items.containsKey(item.getId())) throw new InvalidItemException("Item id- {} does not exist", String.valueOf( item.getId()));
        items.remove(item.getId());
    }

    public boolean exists(T item){
        return items.containsKey(item.getId());
    }

    public List<T> filterByPrice(double minPrice, double maxPrice){

        List<T> filtered = new ArrayList<>();
        for(T item : items.values()){
            if(item.getPrice()>=minPrice && item.getPrice()<=maxPrice) filtered.add(item);
        }
        return filtered;
    }

}

package InventoryManagementSystem;

import InventoryManagementSystem.Entities.Item;

import java.util.*;

public class RecentlyViewedItems<T extends Item> {
    Deque<T> linkedList;
    Set<T> lookUpSet;
    private static final int DEFAULT_CAPACITY = 10;
    private int currSize;

    RecentlyViewedItems(){
        linkedList = new LinkedList<>();
        lookUpSet = new HashSet<>();
        currSize = 0;
    }
    public void add(T item){
        if(lookUpSet.contains(item)) this.remove(item);
        if(currSize == DEFAULT_CAPACITY){
            removeOldest();
        }
        linkedList.addFirst(item);
        currSize++;
    }

    public  void remove(T item){
        linkedList.remove(item);
        currSize--;
    }
    public void removeOldest(){
        try{
            linkedList.removeLast();
            currSize--;
        }catch(NoSuchElementException e){
            System.out.println("No element to remove");
        }

    }

}

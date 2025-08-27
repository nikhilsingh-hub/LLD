package InventoryManagementSystem.Processors;

import java.util.PriorityQueue;
import java.util.Queue;

public class OrderProcessor<T extends Comparable<T>> {
    Queue<T> orders;

    public OrderProcessor(){
        orders = new PriorityQueue<>();
    }

    public void addOrder(T item){
        orders.add(item);
    }
    public T processOrder() {
        return orders.poll();
    }
    public int getSize(){
        return orders.size();
    }
}

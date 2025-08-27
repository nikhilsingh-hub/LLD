package InventoryManagementSystem.Exceptions;

public class DuplicateItemException extends RuntimeException{
    public DuplicateItemException(String id){
        super(id + " already exists in inventory");
    }
}

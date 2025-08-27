package InventoryManagementSystem.Exceptions;

public class InvalidItemException extends  RuntimeException{
    public InvalidItemException(String message, String id){
        super(message + " (ID: " + id + ")");
    }
}

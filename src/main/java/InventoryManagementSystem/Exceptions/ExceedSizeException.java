package InventoryManagementSystem.Exceptions;

public class ExceedSizeException extends RuntimeException{
    public ExceedSizeException(String message) {
        super(message);
    }
}

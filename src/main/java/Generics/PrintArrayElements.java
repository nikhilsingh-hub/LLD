package Generics;

public class PrintArrayElements {
    public static <T> void printArray(T[] arr){
        for(T val : arr){
            System.out.print(val + " ");
        }
    }
}

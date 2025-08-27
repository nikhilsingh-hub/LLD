package Multithreading;

public class Printnum implements Runnable{
    static int x = 0;
    @Override
    public void run(){
        System.out.println("x value: "+x + " from thread "+Thread.currentThread().getName());
    }
}

package Multithreading;

public class Adder implements Runnable {
    Adder(){
    }
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName());
    }

}


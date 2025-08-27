package Multithreading.SyncronisedKeyword;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Objectlock {
    synchronized void fun1() {
        System.out.println("In fun1");
        try{
            Thread.sleep(5000);
        }catch(Exception e){
        }

    }

    synchronized void fun2() {
        System.out.println("In fun2");
    }

    public static synchronized void fun3() {
        System.out.println("In fun3");
        try{
            Thread.sleep(5000);
        }catch(Exception e){
        }
    }

    public static synchronized void fun4() {
        System.out.println("In fun4");
        try{
            Thread.sleep(5000);
        }catch(Exception e){
        }
    }

    public static void main(String[] args) {
        Objectlock objectlock = new Objectlock();
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<?> fut1 = executor.submit(() -> objectlock.fun1());
        Future<?> fut2 = executor.submit(() -> objectlock.fun2());
        Future<?> fut3 = executor.submit(() -> Objectlock.fun3());
        Future<?> fut4 = executor.submit(() -> Objectlock.fun4());

        try{
            fut1.get();
            fut2.get();
            fut3.get();
            fut4.get();
        }catch(Exception e){
        }finally {
            executor.shutdown();
        }

    }
}
package Multithreading;

import Inheritance.C;
import Inheritance.P;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<?> fut1 = executorService.submit(()  -> {
            System.out.println("fut1 is excuted before sleep");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("Future 1 is created");
        Future<?> fut2 = executorService.submit(()  -> {
            System.out.println("fut2 is excuted");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("Future 2 is created");
        Future<?> fut3 = executorService.submit(()  -> System.out.println("fut3 is excuted"));
        System.out.println("Future 3 is created");
        Future<?> fut4 = executorService.submit(()  -> System.out.println("fut4 is excuted"));
        System.out.println("Future 4 is created");

        fut1.get();
        System.out.println("After future 1 get");
        fut2.get();
        System.out.println("After future 2 get");
        fut3.get();
        System.out.println("After future 3 get");
        fut4.get();
        System.out.println("After future 4 get");

        executorService.shutdown();
    }
}

package Multithreading;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        CallablesBasic callablesBasic = new CallablesBasic(n);
        try{
            Future<List<Integer>> future = executorService.submit(callablesBasic);
            List<Integer> retValue = future.get();
            System.out.println(retValue);
        }catch(Exception e){
        }
        executorService.shutdown();
    }
}

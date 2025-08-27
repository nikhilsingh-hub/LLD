package Multithreading.TreeSizeCalculatorModule;

import java.util.Random;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.random.RandomGenerator;

public class Main {
    public static void main(String[] args) {
        Node root = generateTree(2);
        ExecutorService executorService = Executors.newCachedThreadPool();
        TreeSizeCalculator treeSizeCalculator = new TreeSizeCalculator(root, executorService);
        try{
            Timer timer = new Timer();
            Future<Integer> future =  executorService.submit(treeSizeCalculator);
            System.out.println(future.get());
        }catch(Exception e){

        }
    }
    public static Node generateTree(int size){
        if(size == 0)return null;
        Random random = new Random();
        int val = random.nextInt(25);
        Node  node = new Node(val);
        node.left = generateTree(size-1);
        node.right = generateTree(size-1);
        return node;
    }
}

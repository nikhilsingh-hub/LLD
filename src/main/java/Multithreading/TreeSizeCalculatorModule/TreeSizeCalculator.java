package Multithreading.TreeSizeCalculatorModule;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class TreeSizeCalculator implements Callable<Integer> {
    Node currNode;
    ExecutorService executorService;
    TreeSizeCalculator(Node node, ExecutorService excecutorService){
        this.currNode = node;
        this.executorService = excecutorService;
    }
    @Override
    public Integer call() throws Exception {
        if (currNode == null) return 0;
        TreeSizeCalculator treeSizeCalculatorLeft = new TreeSizeCalculator(currNode.left, executorService);
        TreeSizeCalculator treeSizeCalculatorRight = new TreeSizeCalculator(currNode.right, executorService);
        Future<Integer> futureLeft = executorService.submit(treeSizeCalculatorLeft);
        Future<Integer> futureRight = executorService.submit(treeSizeCalculatorRight);
        return 1+ futureRight.get() + futureLeft.get();
    }
}

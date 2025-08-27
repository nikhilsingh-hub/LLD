package Multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CallablesBasic implements Callable<List<Integer>> {
    private int n;
    CallablesBasic(int x){
        this.n = x;
    }
    public List<Integer> call(){
        List<Integer> retList = new ArrayList<>();
        for(int i = 0; i<n; i++){
            retList.add(i);
        }
        return retList;
    }
}

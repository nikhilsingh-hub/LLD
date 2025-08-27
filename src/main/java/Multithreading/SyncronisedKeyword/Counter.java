package Multithreading.SyncronisedKeyword;

public class Counter {
    private int n;
    public Counter(int x){
        this.n = x;
    }
    public synchronized void incValue(int offset){
        n += offset;
    }
    public synchronized int getValue(int offset){
        return n;
    }
    public synchronized void decValue(int offset){
        n -= offset;
    }
}

package Multithreading;

public class TableCreator implements Runnable{
    int n;
    TableCreator(int x){
        this.n = x;
    }
    @Override
    public void run() {
        this.printTable(n);
    }
    private void printTable(int x){
        for(int i = 1; i<=10; i++){
            System.out.println(x+" times "+i+" is "+x*i);
        }
    }
}

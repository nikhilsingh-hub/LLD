package Multithreading.Semaphore;

import java.util.concurrent.Semaphore;

public class PrintJobSystem {
    private static final Semaphore semaphore = new Semaphore(3); // only 3 threads allowed

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            final int jobId = i;
            new Thread(() -> {
                try {
//                    System.out.println("Job " + jobId + " is waiting to print.");
                    semaphore.acquire(); // acquire a permit
                    System.out.println("Job " + jobId + " is printing.");
                    Thread.sleep(3000); // simulate printing time
                    System.out.println("Job " + jobId + " is done printing.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(); // release the permit
                }
            }).start();
        }
    }
}

package Multithreading.ImagePainter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ImagePainter {
    static ExecutorService executor;
    private final int[][] image;

    public ImagePainter(int[][] image) {
        this.image = image;
        executor = Executors.newFixedThreadPool(4);
    }

    public void paintImage(int color) throws InterruptedException {
        int rows = image.length;
        int cols = image[0].length;
        int halfRows = rows / 2;
        int halfCols = cols / 2;

        Future<?> q1 = executor.submit(() -> paintQuadrant(0, halfRows, 0, halfCols, color));
        Future<?> q2 = executor.submit(() -> paintQuadrant(halfRows, rows, 0, halfCols, color*2));
        Future<?> q3 = executor.submit(() -> paintQuadrant(0, halfRows, halfCols, cols, color*3));
        Future<?> q4 = executor.submit(() -> paintQuadrant(halfRows, rows, halfCols, cols, color*4));

        List<Future<?>> futureList = List.of(q1, q2, q3, q4);


        try{
            for (Future<?> x : futureList){
                x.get();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally{
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES); // ensure proper shutdown

        }
    }

    private void paintQuadrant(int startRow, int endRow, int startCol, int endCol, int color) {
        for(int i = startRow; i<endRow; i++){
            for(int j = startCol; j<endCol; j++){
                this.image[i][j] = color;
            }
        }
    }

    public int[][] getImage() {
        return image;
    }

    public static void main(String[] args) throws InterruptedException {
        int[][] image = new int[4][4]; // Example 4x4 image
        ImagePainter painter = new ImagePainter(image);
        painter.paintImage(1);

        int[][] paintedImage = painter.getImage();
        // Print the painted image (for demonstration purposes)
        for (int[] row : paintedImage) {
            for (int pixel : row) {
                System.out.print(pixel + " ");
            }
            System.out.println();
        }
    }
}
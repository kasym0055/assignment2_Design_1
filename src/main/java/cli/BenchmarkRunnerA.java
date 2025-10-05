package cli;

import algorithms.MinHeap;
import metrics.PerformanceTracker;

import java.util.Random;

public class BenchmarkRunnerA {
    public static void main(String[] args) throws Exception {
        int[] sizes = new int[] {100, 1000, 10000, 100000};
        Random rnd = new Random(123);
        StringBuilder rows = new StringBuilder();
        PerformanceTracker tracker = new PerformanceTracker();

        for (int n : sizes) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = rnd.nextInt();

            long start = System.nanoTime();
            MinHeap heap = new MinHeap(arr, true);
            long elapsed = System.nanoTime() - start;
            double ms = elapsed / 1e6;
            rows.append(String.format("%d,%f,0,0,0\n", n, ms));
            System.out.printf("Built min-heap of size %d in %.3f ms\n", n, ms);
        }

        tracker.writeCsv("performance_minheap.csv", "n,time_ms,comparisons,swaps,accesses", rows);
        System.out.println("Wrote CSV: performance_minheap.csv");
    }
}

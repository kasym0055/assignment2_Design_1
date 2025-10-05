package cli;

import algorithms.MaxHeap;
import metrics.PerformanceTracker;

import java.util.Random;
import java.util.Scanner;

/**
 * Simple CLI benchmark runner for MaxHeap operations.
 * Usage:
 *   java -cp target/classes cli.BenchmarkRunner
 *
 * It will run for sizes 100, 1000, 10000, 100000 (if memory allows) and output CSV to /tmp or project folder.
 */
public class BenchmarkRunner {
    public static void main(String[] args) throws Exception {
        int[] sizes = new int[] {100, 1000, 10000, 100000};
        Random rnd = new Random(42);
        StringBuilder rows = new StringBuilder();
        PerformanceTracker tracker = new PerformanceTracker();

        for (int n : sizes) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = rnd.nextInt();

            long start = System.nanoTime();
            MaxHeap heap = new MaxHeap(arr, true); // build heap bottom-up
            long elapsed = System.nanoTime() - start;
            double ms = elapsed / 1e6;
            // No internal tracker here; this runner focuses on wall-clock time.
            rows.append(String.format("%d,%f,0,0,0\n", n, ms));
            System.out.printf("Built heap of size %d in %.3f ms\n", n, ms);
        }

        String out = "performance_heap.csv";
        tracker.writeCsv(out, "n,time_ms,comparisons,swaps,accesses", rows);
        System.out.println("Wrote CSV: " + out);
        System.out.println("Done.");
    }
}

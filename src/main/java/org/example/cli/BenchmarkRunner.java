package org.example.cli;

import org.example.algorithms.heaps.MinHeap;
import org.example.metrics.PerformanceTracker;

import java.io.*; import java.util.*;

public class BenchmarkRunner {
    static int[] genRandom(int n){ Random r=new Random(42); int[] a=new int[n]; for(int i=0;i<n;i++) a[i]=r.nextInt(n*10); return a; }
    static void writeCsv(String file, List<String> rows) throws Exception { try(PrintWriter pw=new PrintWriter(new FileWriter(file))){ for(String r:rows) pw.println(r); } }

    public static void main(String[] args) throws Exception {
        Map<String,String> arg = new HashMap<>();
        for (String s: args) { String[] p = s.split("="); if (p.length==2) arg.put(p[0], p[1]); }
        int n = Integer.parseInt(arg.getOrDefault("size","100000"));
        int trials = Integer.parseInt(arg.getOrDefault("trials","5"));

        List<String> rows = new ArrayList<>();
        rows.add("algo,size,trial,ns,comparisons,swaps,reads,writes,allocations");

        for (int t=0;t<trials;t++) {
            int[] data = genRandom(n);
            PerformanceTracker pt = new PerformanceTracker();
            pt.start();
            MinHeap h = new MinHeap(n, pt);
            for (int x: data) h.push(x);
            for (int i=0;i<Math.max(1,n/20);i++) { // несколько decreaseKey
                int idx = i % Math.max(1, h.size());
                h.decreaseKey(idx, Integer.MIN_VALUE + i);
            }
            while (h.size() > 0) h.pop();
            pt.stop();

            rows.add(String.join(",", Arrays.asList(
                    "minheap", String.valueOf(n), String.valueOf(t),
                    String.valueOf(pt.elapsedNs()), String.valueOf(pt.getComparisons()),
                    String.valueOf(pt.getSwaps()), String.valueOf(pt.getReads()),
                    String.valueOf(pt.getWrites()), String.valueOf(pt.getAllocations())
            )));
        }

        new File("docs/performance-plots").mkdirs();
        String out = "docs/performance-plots/minheap-" + n + ".csv";
        writeCsv(out, rows);
        System.out.println("Wrote CSV: " + out);
    }
}
package org.example.metrics;

public class PerformanceTracker {
    private long comparisons, swaps, reads, writes, allocations, recursion;
    private long startNs, endNs;
    public void start(){ startNs = System.nanoTime(); }
    public void stop(){ endNs = System.nanoTime(); }
    public long elapsedNs(){ return endNs - startNs; }
    public void incComparison(){ comparisons++; }
    public void incSwap(){ swaps++; }
    public void incRead(){ reads++; }
    public void incWrite(){ writes++; }
    public void incAlloc(){ allocations++; }
    public void incRecursion(){ recursion++; }
    public long getComparisons(){ return comparisons; }
    public long getSwaps(){ return swaps; }
    public long getReads(){ return reads; }
    public long getWrites(){ return writes; }
    public long getAllocations(){ return allocations; }
    public long getRecursion(){ return recursion; }
}
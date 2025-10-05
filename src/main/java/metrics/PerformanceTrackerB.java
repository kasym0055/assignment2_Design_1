package metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Simple performance tracker: counts comparisons, swaps, array accesses.
 * Use manually in benchmarking harness when measuring algorithms.
 */
public class PerformanceTrackerB {
    private AtomicLong comparisons = new AtomicLong(0);
    private AtomicLong swaps = new AtomicLong(0);
    private AtomicLong accesses = new AtomicLong(0);

    public void addComparison(long n) { comparisons.addAndGet(n); }
    public void addSwap(long n) { swaps.addAndGet(n); }
    public void addAccess(long n) { accesses.addAndGet(n); }

    public long getComparisons() { return comparisons.get(); }
    public long getSwaps() { return swaps.get(); }
    public long getAccesses() { return accesses.get(); }

    public void reset() {
        comparisons.set(0);
        swaps.set(0);
        accesses.set(0);
    }

    public String toCsvLine(int n, double timeMs) {
        return String.format("%d,%f,%d,%d,%d\n", n, timeMs, getComparisons(), getSwaps(), getAccesses());
    }

    public void writeCsv(String path, String header, StringBuilder rows) throws IOException {
        try (FileWriter fw = new FileWriter(path)) {
            fw.write(header);
            fw.write("\n");
            fw.write(rows.toString());
        }
    }
}

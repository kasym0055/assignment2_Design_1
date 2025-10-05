package algorithms.heaps;

import org.example.algorithms.heaps.MinHeap;
import org.example.metrics.PerformanceTracker;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinHeapTest {
    @Test
    public void pushPeekPop() {
        PerformanceTracker t = new PerformanceTracker();
        MinHeap h = new MinHeap(2, t);
        h.push(5); h.push(3); h.push(7); h.push(4);
        assertEquals(3, h.peek());
        assertEquals(3, h.pop());
        assertEquals(4, h.pop());
        assertEquals(5, h.pop());
        assertEquals(7, h.pop());
    }

    @Test
    public void decreaseKeyWorks() {
        PerformanceTracker t = new PerformanceTracker();
        MinHeap h = new MinHeap(4, t);
        h.push(10); h.push(20); h.push(30); h.push(40);
        h.decreaseKey(2, 1); // 30 -> 1
        assertEquals(1, h.peek());
    }

    @Test
    public void mergeTwoHeaps() {
        PerformanceTracker t = new PerformanceTracker();
        MinHeap a = new MinHeap(4, t); MinHeap b = new MinHeap(4, t);
        a.push(5); a.push(1); a.push(9);
        b.push(2); b.push(6);
        MinHeap c = MinHeap.merge(a, b);
        assertEquals(5, c.size());
        assertEquals(1, c.pop());
        assertEquals(2, c.pop());
    }
}
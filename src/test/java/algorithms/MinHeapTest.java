package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MinHeapTest {

    @Test
    public void testInsertAndExtract() {
        MinHeap h = new MinHeap(4);
        h.insert(10);
        h.insert(5);
        h.insert(20);
        assertEquals(3, h.getSize());
        assertEquals(5, h.extractMin());
        assertEquals(10, h.extractMin());
        assertEquals(20, h.extractMin());
    }

    @Test
    public void testDecreaseKey() {
        MinHeap h = new MinHeap(new int[]{5,6,7,8}, true);
        h.insert(100);
        int idx = h.getSize();
        h.decreaseKey(idx, 1);
        assertEquals(1, h.extractMin());
    }

    @Test
    public void testBuildHeapAndPeek() {
        int[] arr = new int[]{9,3,4,1,5,2};
        MinHeap h = new MinHeap(arr, true);
        assertEquals(1, h.peek());
    }

    @Test
    public void testExtractEmptyThrows() {
        MinHeap h = new MinHeap(2);
        assertThrows(java.util.NoSuchElementException.class, () -> h.extractMin());
    }
}

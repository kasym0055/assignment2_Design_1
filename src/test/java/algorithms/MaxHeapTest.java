package algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MaxHeapTest {

    @Test
    public void testInsertAndExtract() {
        MaxHeap h = new MaxHeap(4);
        h.insert(10);
        h.insert(5);
        h.insert(20);
        assertEquals(3, h.getSize());
        assertEquals(20, h.extractMax());
        assertEquals(10, h.extractMax());
        assertEquals(5, h.extractMax());
    }

    @Test
    public void testIncreaseKey() {
        MaxHeap h = new MaxHeap(new int[]{1,2,3,4}, true);
        // heap should be built into max-heap; find index of value 1 and increase to 100
        // Since internal structure is not exposed, we'll insert then increase last position
        h.insert(0);
        int idx = h.getSize(); // last inserted
        h.increaseKey(idx, 100);
        assertEquals(100, h.extractMax());
    }

    @Test
    public void testBuildHeapAndToArray() {
        int[] arr = new int[]{3,1,4,1,5,9,2,6,5};
        MaxHeap h = new MaxHeap(arr, true);
        int[] out = h.toArray();
        assertEquals(arr.length, out.length);
        // max should be first when peek
        assertEquals(9, h.peek());
    }

    @Test
    public void testExtractEmptyThrows() {
        MaxHeap h = new MaxHeap(2);
        assertThrows(java.util.NoSuchElementException.class, () -> h.extractMax());
    }
}

package algorithms;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * In-place Max-Heap implementation using 1-based indexing for easier parent/child math.
 * Supports insert, increaseKey (increase-key), extractMax, peek, buildHeap (bottom-up),
 * and merge (merging two heaps) via array concatenation + buildHeap.
 *
 * Tracks key operations should be done by external PerformanceTracker when benchmarking.
 */
public class MaxHeap {
    private int[] heap; // 1-based index: heap[0] unused
    private int size;

    public MaxHeap(int capacity) {
        heap = new int[capacity + 1];
        size = 0;
    }

    public MaxHeap(int[] arr, boolean build) {
        // create from array (arr is 0-based), copy to 1-based heap array
        heap = new int[arr.length + 1];
        System.arraycopy(arr, 0, heap, 1, arr.length);
        size = arr.length;
        if (build) buildHeap();
    }

    public int getSize() { return size; }

    private int parent(int i) { return i / 2; }
    private int left(int i) { return 2 * i; }
    private int right(int i) { return 2 * i + 1; }

    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    public void insert(int key) {
        if (size + 1 >= heap.length) {
            // grow capacity
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
        size++;
        heap[size] = Integer.MIN_VALUE; // sentinel
        increaseKey(size, key);
    }

    /**
     * increaseKey increases value at index i (1-based) to newKey; newKey must be >= current.
     */
    public void increaseKey(int i, int newKey) {
        if (i < 1 || i > size) throw new IndexOutOfBoundsException("Index out of heap range");
        if (newKey < heap[i]) throw new IllegalArgumentException("new key is smaller than current key");
        heap[i] = newKey;
        // bubble up
        while (i > 1 && heap[parent(i)] < heap[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public int peek() {
        if (size < 1) throw new NoSuchElementException("heap is empty");
        return heap[1];
    }

    public int extractMax() {
        if (size < 1) throw new NoSuchElementException("heap underflow");
        int max = heap[1];
        heap[1] = heap[size];
        size--;
        maxHeapify(1);
        return max;
    }

    private void maxHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int largest = i;
        if (l <= size && heap[l] > heap[largest]) largest = l;
        if (r <= size && heap[r] > heap[largest]) largest = r;
        if (largest != i) {
            swap(i, largest);
            maxHeapify(largest);
        }
    }

    public void buildHeap() {
        for (int i = size / 2; i >= 1; i--) {
            maxHeapify(i);
        }
    }

    /**
     * Merge another MaxHeap into this one by concatenating arrays and building heap (O(n+m)).
     */
    public void merge(MaxHeap other) {
        int newCapacity = (this.size + other.size) + 1;
        int[] newArr = new int[newCapacity + 5];
        System.arraycopy(this.heap, 1, newArr, 1, this.size);
        System.arraycopy(other.heap, 1, newArr, this.size + 1, other.size);
        this.heap = newArr;
        this.size = this.size + other.size;
        buildHeap();
    }

    // For testing and benchmarking: return heap as 0-based array of size 'size'
    public int[] toArray() {
        int[] arr = new int[size];
        System.arraycopy(heap, 1, arr, 0, size);
        return arr;
    }
}

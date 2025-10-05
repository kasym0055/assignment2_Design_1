package algorithms;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * In-place Min-Heap implementation using 1-based indexing.
 * Supports insert, decreaseKey (decrease-key), extractMin, peek, buildHeap (bottom-up),
 * and merge (concatenate arrays + buildHeap).
 *
 * Designed for student A (Min-Heap).
 */
public class MinHeap {
    private int[] heap; // 1-based index
    private int size;

    public MinHeap(int capacity) {
        heap = new int[capacity + 1];
        size = 0;
    }

    public MinHeap(int[] arr, boolean build) {
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
        int t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
    }

    public void insert(int key) {
        if (size + 1 >= heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
        size++;
        heap[size] = Integer.MAX_VALUE;
        decreaseKey(size, key);
    }

    /**
     * decreaseKey decreases the value at index i (1-based) to newKey; newKey must be <= current.
     */
    public void decreaseKey(int i, int newKey) {
        if (i < 1 || i > size) throw new IndexOutOfBoundsException("Index out of heap range");
        if (newKey > heap[i]) throw new IllegalArgumentException("new key is larger than current key");
        heap[i] = newKey;
        while (i > 1 && heap[parent(i)] > heap[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public int peek() {
        if (size < 1) throw new NoSuchElementException("heap is empty");
        return heap[1];
    }

    public int extractMin() {
        if (size < 1) throw new NoSuchElementException("heap underflow");
        int min = heap[1];
        heap[1] = heap[size];
        size--;
        minHeapify(1);
        return min;
    }

    private void minHeapify(int i) {
        int l = left(i), r = right(i), smallest = i;
        if (l <= size && heap[l] < heap[smallest]) smallest = l;
        if (r <= size && heap[r] < heap[smallest]) smallest = r;
        if (smallest != i) {
            swap(i, smallest);
            minHeapify(smallest);
        }
    }

    public void buildHeap() {
        for (int i = size / 2; i >= 1; i--) {
            minHeapify(i);
        }
    }

    /**
     * Merge another MinHeap into this by concatenating and building heap (O(n+m)).
     */
    public void merge(MinHeap other) {
        int newSize = this.size + other.size;
        int[] newArr = new int[newSize + 5];
        System.arraycopy(this.heap, 1, newArr, 1, this.size);
        System.arraycopy(other.heap, 1, newArr, this.size + 1, other.size);
        this.heap = newArr;
        this.size = newSize;
        buildHeap();
    }

    public int[] toArray() {
        int[] arr = new int[size];
        System.arraycopy(heap, 1, arr, 0, size);
        return arr;
    }
}

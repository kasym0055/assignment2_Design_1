package org.example.algorithms.heaps;

import org.example.metrics.PerformanceTracker;

import java.util.Arrays;
public class MinHeap {
    private int[] heap;
    private int size;
    private final PerformanceTracker t;

    public MinHeap(int capacity, PerformanceTracker t) {
        this.heap = new int[Math.max(1, capacity)]; this.size = 0; this.t = t; t.incAlloc();
    }

    private void ensure(int cap) {
        if (cap <= heap.length) return;
        heap = Arrays.copyOf(heap, Math.max(cap, heap.length << 1)); t.incAlloc();
    }

    private void swap(int i, int j) {
        int tmp = heap[i]; heap[i] = heap[j]; heap[j] = tmp;
        t.incSwap(); t.incWrite(); t.incWrite();
    }

    private void siftUp(int i) {
        while (i > 0) {
            int p = (i - 1) >> 1;
            t.incRead(); t.incRead(); t.incComparison();
            if (heap[p] <= heap[i]) break;
            swap(p, i); i = p;
        }
    }

    private void siftDown(int i) {
        while (true) {
            int l = (i << 1) + 1, r = l + 1, m = i;
            if (l < size) { t.incRead(); t.incRead(); t.incComparison(); if (heap[l] < heap[m]) m = l; }
            if (r < size) { t.incRead(); t.incRead(); t.incComparison(); if (heap[r] < heap[m]) m = r; }
            if (m == i) break;
            swap(i, m); i = m;
        }
    }

    public void push(int x) { ensure(size + 1); heap[size] = x; t.incWrite(); siftUp(size++); }
    public int peek() { if (size == 0) throw new IllegalStateException("empty"); t.incRead(); return heap[0]; }
    public int pop() {
        if (size == 0) throw new IllegalStateException("empty");
        int res = heap[0]; t.incRead(); heap[0] = heap[--size]; t.incWrite(); siftDown(0); return res;
    }

    public void decreaseKey(int index, int newVal) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        t.incComparison(); if (newVal > heap[index]) throw new IllegalArgumentException("newVal > current");
        heap[index] = newVal; t.incWrite(); siftUp(index);
    }

    public int size() { return size; }


    public static MinHeap merge(MinHeap a, MinHeap b) {
        PerformanceTracker t = a.t; // считаем в одном трекере
        int[] arr = new int[a.size + b.size]; t.incAlloc();
        System.arraycopy(a.heap, 0, arr, 0, a.size); t.incWrite();
        System.arraycopy(b.heap, 0, arr, a.size, b.size); t.incWrite();
        MinHeap h = new MinHeap(1, t);
        h.heap = arr; h.size = arr.length;
        for (int i = (h.size >> 1) - 1; i >= 0; i--) h.siftDown(i);
        return h;
    }
}
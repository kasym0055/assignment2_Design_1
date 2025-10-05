# Assignment 2 — Pair 4: Min-Heap (Student A)

## Overview
This repository contains the implementation of a **Min-Heap** data structure with the following operations:
- `push` (insert element)
- `peek` (get the minimum element)
- `pop` (extract the minimum element)
- `decreaseKey` (decrease the value of a given node)
- `merge` (merge two heaps in O(n+m) time via bottom-up heapify)

The project includes:
- **PerformanceTracker** to measure comparisons, swaps, reads/writes, allocations  
- **CLI BenchmarkRunner** to generate CSV benchmark data  
- **Unit tests** for correctness and edge cases  

---

## Requirements
- Java 17+
- Maven 3.8+

---

## Build & Test
```bash
# Run all unit tests
mvn test

# Package into a runnable JAR
mvn -DskipTests package

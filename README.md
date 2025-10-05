# assignment2-min-heap (Student A)

**Algorithm:** Min-Heap implementation (in-place, 1-based indexing)  
**Student role:** Student A (Min-Heap Implementation with decrease-key and merge)

## Structure
- `src/main/java/algorithms/MinHeap.java` - main implementation
- `src/main/java/metrics/PerformanceTracker.java` - simple counters & CSV writer
- `src/main/java/cli/BenchmarkRunner.java` - simple CLI benchmark runner
- `src/test/java/algorithms/MinHeapTest.java` - JUnit 5 tests
- `docs/analysis-report.pdf` - individual analysis report placeholder
- `docs/performance-plots/` - place for plots

## How to build & test
Requires Java 17 and Maven.

Build:
```
mvn -q -DskipTests package
```

Run tests:
```
mvn test
```

Run benchmark:
```
java -cp target/classes cli.BenchmarkRunner
```


# assignment2-max-heap (Student B)

**Algorithm:** Max-Heap implementation (in-place, 1-based indexing)  
**Student role:** Student B (Max-Heap Implementation with increase-key and extract-max)

## Structure
- `src/main/java/algorithms/MaxHeap.java` - main implementation
- `src/main/java/metrics/PerformanceTracker.java` - simple counters & CSV writer
- `src/main/java/cli/BenchmarkRunner.java` - simple CLI benchmark runner
- `src/test/java/algorithms/MaxHeapTest.java` - JUnit 5 tests
- `docs/analysis-report.pdf` - individual analysis report placeholder
- `docs/performance-plots/` - place for plots (empty)

## How to build & test
Requires Java 17 and Maven.

Build:
```
mvn -q -DskipTests package
```

Run tests:
```
mvn test
```

Run benchmark (after building classes):
```
java -cp target/classes:target/test-classes cli.BenchmarkRunnerB
```

## Notes
- Implementation uses 1-based indexing internally for simpler math.
- increaseKey(i, newKey) assumes the caller knows the 1-based index; in real-world use you'd typically expose increaseKey by value or maintain handles.
- PerformanceTracker is a simple helper; for precise microbenchmarks consider JMH (an example commit message in the assignment suggests JMH).



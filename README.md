# Assignment 1 — Divide & Conquer Algorithms

## Objectives
- Implement and analyze four classical divide-and-conquer algorithms.
- Collect runtime metrics (time, recursion depth, comparisons, swaps, allocations).
- Compare theoretical recurrence relations (Master Theorem / Akra–Bazzi) with experimental results.
- Demonstrate proper software engineering practices (modular code, tests, Git branching, documentation).

---

## Implemented Algorithms

1. **MergeSort**
    - Recursive top-down implementation.
    - Optimizations: buffer reuse and cutoff to insertion sort.
    - Time complexity: `Θ(n log n)` (Master theorem, case 2).

2. **QuickSort (randomized)**
    - Random pivot selection.
    - Recurse on the smaller partition first → stack depth `O(log n)`.
    - Average complexity: `Θ(n log n)`, worst case `Θ(n²)`.

3. **Deterministic Select (Median-of-Medians)**
    - Groups of 5 elements → median of medians pivot.
    - Partitioning done in-place.
    - Recurrence: `T(n) ≤ T(n/5) + T(7n/10) + Θ(n)` → `Θ(n)`.

4. **Closest Pair of Points (2D)**
    - Divide-and-conquer with presorting by x and y.
    - Strip method with at most 7 neighbor checks.
    - Recurrence: `T(n) = 2T(n/2) + Θ(n)` → `Θ(n log n)`.

---

## CLI Usage

Compile with Maven:
```bash
mvn clean package


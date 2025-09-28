package edu.algos.metrics;

import java.util.concurrent.atomic.AtomicLong;

public class Metrics {
    public final AtomicLong comparisons = new AtomicLong();
    public final AtomicLong swaps = new AtomicLong();
    public final AtomicLong allocations = new AtomicLong();
    private final ThreadLocal<Integer> depth = ThreadLocal.withInitial(() -> 0);
    private final ThreadLocal<Integer> maxDepth = ThreadLocal.withInitial(() -> 0);

    public void incComparisons(long v){ comparisons.addAndGet(v); }
    public void incSwaps(long v){ swaps.addAndGet(v); }
    public void incAllocations(long v){ allocations.addAndGet(v); }

    public void enterRecursion(){
        depth.set(depth.get() + 1);
        if(depth.get() > maxDepth.get()) maxDepth.set(depth.get());
    }
    public void exitRecursion(){
        depth.set(depth.get() - 1);
    }
    public int getMaxDepth(){ return maxDepth.get(); }
    public void resetDepth(){
        depth.set(0);
        maxDepth.set(0);
    }
    public void resetAll(){
        comparisons.set(0);
        swaps.set(0);
        allocations.set(0);
        resetDepth();
    }

    @Override
    public String toString(){
        return "Metrics{" +
                "comparisons=" + comparisons +
                ", swaps=" + swaps +
                ", allocations=" + allocations +
                ", maxDepth=" + getMaxDepth() +
                '}';
    }
}


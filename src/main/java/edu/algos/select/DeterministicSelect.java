package edu.algos.select;

import edu.algos.metrics.Metrics;

public class DeterministicSelect {
    private final Metrics metrics;

    public DeterministicSelect(Metrics m){ this.metrics = m; }

    // find k-th smallest (0-based)
    public int select(int[] a, int k){
        if(k < 0 || k >= a.length) throw new IllegalArgumentException();
        return select(a, 0, a.length-1, k);
    }

    private int select(int[] a, int lo, int hi, int k){
        while(true){
            if(lo==hi) return a[lo];
            metrics.enterRecursion();
            int pivot = medianOfMedians(a, lo, hi);
            int pivotIndex = partition(a, lo, hi, pivot);
            int rank = pivotIndex - lo;
            if(k == rank){
                metrics.exitRecursion();
                return a[pivotIndex];
            } else if(k < rank){
                hi = pivotIndex - 1;
            } else {
                k = k - rank - 1;
                lo = pivotIndex + 1;
            }
            metrics.exitRecursion();
        }
    }

    private int partition(int[] a, int lo, int hi, int pivotValue){
        int i = lo;
        int store = lo;
        // find pivot index and move pivot to end
        int pivotIndex = lo;
        for(int j=lo;j<=hi;j++){
            metrics.incComparisons(1);
            if(a[j]==pivotValue){ pivotIndex = j; break; }
        }
        swap(a, pivotIndex, hi);
        for(i=lo;i<hi;i++){
            metrics.incComparisons(1);
            if(a[i] < pivotValue){
                swap(a, i, store++);
            }
        }
        swap(a, store, hi);
        return store;
    }

    private int medianOfMedians(int[] a, int lo, int hi){
        int n = hi - lo + 1;
        if(n <= 5){
            insertionSort(a, lo, hi);
            return a[lo + n/2];
        }
        int numMedians = (n + 4) / 5;
        for(int i=0;i<numMedians;i++){
            int subLo = lo + i*5;
            int subHi = Math.min(subLo + 4, hi);
            insertionSort(a, subLo, subHi);
            int medianIndex = subLo + (subHi - subLo) / 2;
            swap(a, lo + i, medianIndex); // move medians to front
        }
        // recursively find median of medians
        return select(a, lo, lo + numMedians - 1, numMedians/2);
    }

    private void insertionSort(int[] a, int l, int r){
        for(int i=l+1;i<=r;i++){
            int key = a[i];
            int j=i-1;
            while(j>=l){
                metrics.incComparisons(1);
                if(a[j] > key){
                    a[j+1] = a[j];
                    j--;
                } else break;
            }
            a[j+1] = key;
        }
    }

    private void swap(int[] a, int i, int j){
        int t = a[i]; a[i] = a[j]; a[j] = t;
        metrics.incSwaps(1);
    }
}

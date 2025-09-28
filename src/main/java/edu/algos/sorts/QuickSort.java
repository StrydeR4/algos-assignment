package edu.algos.sorts;

import edu.algos.metrics.Metrics;
import java.util.Random;

public class QuickSort {
    private final Metrics metrics;
    private final Random rnd = new Random();

    public QuickSort(Metrics m){ this.metrics = m; }

    public void sort(int[] a){
        if(a==null || a.length<2) return;
        quicksort(a, 0, a.length-1);
    }

    private void quicksort(int[] a, int lo, int hi){
        while(lo < hi){
            if(hi - lo < 16){ // small cutoff to insertion
                insertion(a, lo, hi);
                return;
            }
            metrics.enterRecursion();
            int pIdx = lo + rnd.nextInt(hi - lo + 1);
            int pivot = a[pIdx];
            // move pivot to end
            swap(a, pIdx, hi);
            int store = lo;
            for(int i=lo;i<hi;i++){
                metrics.incComparisons(1);
                if(a[i] < pivot){
                    swap(a,i,store++);
                }
            }
            swap(a, store, hi);
            int leftSize = store - lo;
            int rightSize = hi - store;
            // recurse into smaller side
            if(leftSize < rightSize){
                quicksort(a, lo, store-1);
                lo = store+1; // iterate on larger side
            } else {
                quicksort(a, store+1, hi);
                hi = store-1;
            }
            metrics.exitRecursion();
        }
    }

    private void swap(int[] a, int i, int j){
        if(i==j) return;
        int t = a[i]; a[i] = a[j]; a[j] = t;
        metrics.incSwaps(1);
    }

    private void insertion(int[] a, int lo, int hi){
        for(int i=lo+1;i<=hi;i++){
            int key = a[i];
            int j=i-1;
            while(j>=lo){
                metrics.incComparisons(1);
                if(a[j] > key){
                    a[j+1] = a[j];
                    metrics.incSwaps(1);
                    j--;
                } else break;
            }
            a[j+1] = key;
        }
    }
}

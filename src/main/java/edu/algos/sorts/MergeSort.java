package edu.algos.sorts;

import edu.algos.metrics.Metrics;

public class MergeSort {
    private final Metrics metrics;
    private final int CUT_OFF = 32; // small-n cutoff to insertion sort

    public MergeSort(Metrics m){ this.metrics = m; }

    public void sort(int[] a){
        if(a==null || a.length<2) return;
        int[] buf = new int[a.length];
        metrics.incAllocations(1);
        mergesort(a, buf, 0, a.length);
    }

    private void mergesort(int[] a, int[] buf, int l, int r){
        if(r - l <= CUT_OFF){
            insertionSort(a, l, r);
            return;
        }
        metrics.enterRecursion();
        int m = l + ((r-l)>>1);
        mergesort(a, buf, l, m);
        mergesort(a, buf, m, r);
        merge(a, buf, l, m, r);
        metrics.exitRecursion();
    }

    private void merge(int[] a, int[] buf, int l, int m, int r){
        int i=l, j=m, k=l;
        while(i<m && j<r){
            metrics.incComparisons(1);
            if(a[i] <= a[j]) buf[k++] = a[i++];
            else buf[k++] = a[j++];
        }
        while(i<m) buf[k++] = a[i++];
        while(j<r) buf[k++] = a[j++];
        System.arraycopy(buf, l, a, l, r-l);
    }

    private void insertionSort(int[] a, int l, int r){
        for(int i = l+1; i<r; i++){
            int key = a[i];
            int j = i-1;
            while(j>=l){
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


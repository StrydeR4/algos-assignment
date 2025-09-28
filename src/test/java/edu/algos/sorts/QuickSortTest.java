package edu.algos.sorts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.algos.util.ArrayUtil;
import edu.algos.metrics.Metrics;

public class QuickSortTest {

    @Test
    void testSortRandomArray() {
        int[] arr = ArrayUtil.randomIntArray(1000, 10000);
        QuickSort qs = new QuickSort(new Metrics());
        qs.sort(arr);
        assertTrue(ArrayUtil.isSorted(arr), "Array should be sorted after QuickSort");
    }

    @Test
    void testSortAlreadySortedArray() {
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) arr[i] = i;
        QuickSort qs = new QuickSort(new Metrics());
        qs.sort(arr);
        assertTrue(ArrayUtil.isSorted(arr), "Already sorted array should remain sorted");
    }

    @Test
    void testSortArrayWithDuplicates() {
        int[] arr = {5, 3, 8, 3, 9, 1, 3, 7};
        QuickSort qs = new QuickSort(new Metrics());
        qs.sort(arr);
        assertTrue(ArrayUtil.isSorted(arr), "Array with duplicates should be sorted");
    }
}

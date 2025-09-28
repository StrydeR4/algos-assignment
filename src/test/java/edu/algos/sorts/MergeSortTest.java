package edu.algos.sorts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import edu.algos.util.ArrayUtil;

public class MergeSortTest {
    @Test
    void testSort() {
        int[] arr = ArrayUtil.randomIntArray(100, 1000);
        MergeSort ms = new MergeSort(null);
        ms.sort(arr);
        assertTrue(ArrayUtil.isSorted(arr));
    }
}

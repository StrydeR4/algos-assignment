package edu.algos.select;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.algos.util.ArrayUtil;
import edu.algos.metrics.Metrics;

import java.util.Arrays;

public class DeterministicSelectTest {

    @Test
    void testSelectMedianRandomArray() {
        int[] arr = ArrayUtil.randomIntArray(1000, 10000);
        int k = arr.length / 2;

        DeterministicSelect ds = new DeterministicSelect(new Metrics());
        int selected = ds.select(arr.clone(), k); // работаем на копии

        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        assertEquals(sorted[k], selected, "DeterministicSelect should find the correct median");
    }

    @Test
    void testSelectMinimum() {
        int[] arr = {7, 2, 9, 4, 1, 6};
        DeterministicSelect ds = new DeterministicSelect(new Metrics());
        int selected = ds.select(arr.clone(), 0); // минимальный элемент
        assertEquals(1, selected, "Minimum should be 1");
    }

    @Test
    void testSelectMaximum() {
        int[] arr = {7, 2, 9, 4, 1, 6};
        DeterministicSelect ds = new DeterministicSelect(new Metrics());
        int selected = ds.select(arr.clone(), arr.length - 1); // максимальный элемент
        assertEquals(9, selected, "Maximum should be 9");
    }
}

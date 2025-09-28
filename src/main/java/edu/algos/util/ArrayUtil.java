package edu.algos.util;

import java.util.Random;

public class ArrayUtil {
    private static final Random rnd = new Random();

    public static int[] randomIntArray(int n, int maxVal){
        int[] a = new int[n];
        for(int i=0;i<n;i++) a[i] = rnd.nextInt(maxVal);
        return a;
    }

    public static int[] copy(int[] a){
        int[] b = new int[a.length];
        System.arraycopy(a,0,b,0,a.length);
        return b;
    }

    public static boolean isSorted(int[] a){
        for(int i=1;i<a.length;i++) if(a[i-1] > a[i]) return false;
        return true;
    }
}

package edu.algos.cli;

import edu.algos.metrics.Metrics;
import edu.algos.sorts.MergeSort;
import edu.algos.sorts.QuickSort;
import edu.algos.select.DeterministicSelect;
import edu.algos.closest.ClosestPair;
import edu.algos.closest.ClosestPair.Point;
import edu.algos.util.ArrayUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length < 4){
            System.out.println("Usage: <algorithm> <n> <trials> <out.csv>");
            System.out.println("algorithm: mergesort|quicksort|select|closest");
            return;
        }
        String algo = args[0];
        int n = Integer.parseInt(args[1]);
        int trials = Integer.parseInt(args[2]);
        String out = args[3];

        try(FileWriter fw = new FileWriter(out)){
            fw.write("n,trial,time_ms,maxDepth,comparisons,swaps,allocations\n");
            Random rnd = new Random(42);
            for(int t=0;t<trials;t++){
                Metrics m = new Metrics();
                long t0 = System.currentTimeMillis();
                if("mergesort".equalsIgnoreCase(algo)){
                    int[] a = ArrayUtil.randomIntArray(n, n*10);
                    MergeSort ms = new MergeSort(m);
                    ms.sort(a);
                    if(!ArrayUtil.isSorted(a)) System.err.println("merge NOT sorted!");
                } else if("quicksort".equalsIgnoreCase(algo)){
                    int[] a = ArrayUtil.randomIntArray(n, n*10);
                    QuickSort qs = new QuickSort(m);
                    qs.sort(a);
                    if(!ArrayUtil.isSorted(a)) System.err.println("quick NOT sorted!");
                } else if("select".equalsIgnoreCase(algo)){
                    int[] a = ArrayUtil.randomIntArray(n, n*10);
                    DeterministicSelect ds = new DeterministicSelect(m);
                    int k = n/2;
                    int val = ds.select(a, k);
                    int check = Arrays.stream(a).sorted().toArray()[k];
                    if(val != check) System.err.println("select mismatch!");
                } else if("closest".equalsIgnoreCase(algo)){
                    Point[] pts = new Point[n];
                    for(int i=0;i<n;i++) pts[i] = new Point(rnd.nextDouble()*10000, rnd.nextDouble()*10000);
                    ClosestPair cp = new ClosestPair(m);
                    double d = cp.closestDistance(pts);
                } else {
                    System.err.println("Unknown algorithm");
                    return;
                }
                long t1 = System.currentTimeMillis();
                fw.write(String.format("%d,%d,%d,%d,%d,%d,%d\n",
                        n, t, (t1-t0),
                        m.getMaxDepth(),
                        m.comparisons.get(),
                        m.swaps.get(),
                        m.allocations.get()));
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("Done; output to " + out);
    }
}


package edu.algos.closest;

import edu.algos.metrics.Metrics;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {
    private final Metrics metrics;

    public static class Point { public final double x,y; public Point(double x,double y){this.x=x;this.y=y;} }

    public ClosestPair(Metrics metrics){ this.metrics = metrics; }

    public double closestDistance(Point[] pts){
        if(pts==null || pts.length<2) return Double.POSITIVE_INFINITY;
        Point[] byX = pts.clone();
        Arrays.sort(byX, Comparator.comparingDouble(p->p.x));
        Point[] byY = pts.clone();
        Arrays.sort(byY, Comparator.comparingDouble(p->p.y));
        return rec(byX, byY, 0, byX.length);
    }

    private double rec(Point[] byX, Point[] byY, int l, int r){
        int n = r - l;
        if(n <= 3) return brute(byX, l, r);
        metrics.enterRecursion();
        int mid = (l + r) >> 1;
        double midx = byX[mid].x;
        // split byY into left and right arrays
        Point[] leftY = new Point[mid - l];
        Point[] rightY = new Point[r - mid];
        int li = 0, ri = 0;
        for(Point p : byY){
            metrics.incComparisons(1);
            if(p.x <= midx) leftY[li++] = p;
            else rightY[ri++] = p;
        }
        double dl = rec(byX, leftY, l, mid);
        double dr = rec(byX, rightY, mid, r);
        double d = Math.min(dl, dr);

        // build strip (points within d of mid line) in y-order
        Point[] strip = new Point[byY.length];
        int s = 0;
        for(Point p : byY){
            if(Math.abs(p.x - midx) < d) strip[s++] = p;
        }
        for(int i=0;i<s;i++){
            // check up to next 7 points
            for(int j=i+1;j<s && j<=i+7;j++){
                double dy = strip[j].y - strip[i].y;
                if(dy >= d) break;
                double dist = dist(strip[i], strip[j]);
                if(dist < d) d = dist;
            }
        }
        metrics.exitRecursion();
        return d;
    }

    private double brute(Point[] a, int l, int r){
        double min = Double.POSITIVE_INFINITY;
        for(int i=l;i<r;i++){
            for(int j=i+1;j<r;j++){
                double d = dist(a[i], a[j]);
                if(d < min) min = d;
            }
        }
        return min;
    }

    private double dist(Point a, Point b){
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.hypot(dx, dy);
    }
}

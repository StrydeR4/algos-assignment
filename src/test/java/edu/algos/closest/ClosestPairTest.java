package edu.algos.closest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.algos.metrics.Metrics;
import edu.algos.closest.ClosestPair.Point;

public class ClosestPairTest {

    // Вспомогательный метод: наивный O(n^2) поиск
    private double bruteForceClosest(Point[] pts) {
        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                double dx = pts[i].x - pts[j].x;
                double dy = pts[i].y - pts[j].y;
                double dist = Math.sqrt(dx * dx + dy * dy);
                minDist = Math.min(minDist, dist);
            }
        }
        return minDist;
    }

    @Test
    void testSmallFixedSet() {
        Point[] pts = {
                new Point(0, 0),
                new Point(3, 4),
                new Point(7, 1),
                new Point(2, 2)
        };
        ClosestPair cp = new ClosestPair(new Metrics());
        double d = cp.closestDistance(pts);

        double expected = bruteForceClosest(pts);
        assertEquals(expected, d, 1e-9, "Closest distance should match brute force");
    }

    @Test
    void testRandomPoints() {
        Point[] pts = new Point[50];
        for (int i = 0; i < pts.length; i++) {
            pts[i] = new Point(Math.random() * 1000, Math.random() * 1000);
        }
        ClosestPair cp = new ClosestPair(new Metrics());
        double d = cp.closestDistance(pts);

        double expected = bruteForceClosest(pts);
        assertEquals(expected, d, 1e-9, "Closest distance should match brute force");
    }
}

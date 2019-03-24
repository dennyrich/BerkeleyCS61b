package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug. Demonstrates how you can use either
 * System.currentTimeMillis or the Princeton Stopwatch
 * class to time code.
 */
public class TimingTestDemo {
    public static void main(String[] args) {
        int C = 2500;
        int numIncrements = 10000;
        int item = 0;
        double[] times = new double[numIncrements];
        ArrayHeapMinPQ<Integer> timeTestHeap = new ArrayHeapMinPQ<>();
        for (int trial = 0; trial < numIncrements; trial++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < C; i++) {
                timeTestHeap.add(item, Math.pow(-1, i) * i + trial);
                item++;
            }
            long end = System.currentTimeMillis();
            times[trial] = (end - start);
            //System.out.println("my implementation: " + (end - start) / 1000.0 + " seconds.");
        }
        for (int i = 0; i < numIncrements; i++) {
            System.out.println(times[i]);
        }

        Stopwatch sw = new Stopwatch();
        NaiveMinPQ<Integer> naive = new NaiveMinPQ<>();
        for (int i = 0; i < 2000000; i++) {
            naive.add(i, Math.pow(-1, i) * i);
        }
        System.out.println("naive: " + sw.elapsedTime() +  " seconds.");
    }
}

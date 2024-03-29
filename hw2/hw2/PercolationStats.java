package hw2;

//import java.util.ArrayList;
//import java.util.List;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int N;
    private int T;
    private double[] trialResults;
    private PercolationFactory pf;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("number of trials and grid size must be positive");
        }
        this.N = N;
        this.T = T;
        this.pf = pf;
        trialResults = new double[T];
        for (int i = 0; i < T; i++) {
            trialResults[i] = trial();
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(trialResults);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(trialResults);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

    private double trial() {
        Percolation test = pf.make(N);
        /*
        List<Integer> available = new ArrayList<>(N * N);
        for (int i = 0; i < N * N; i++) {
            available.add(i);
        }
        int index;

        while (!test.percolates()) {
            if (available.size() > 0) {
                index = StdRandom.uniform(available.size());
            } else {
                index = 0;
            }

            int item = available.get(index);
            available.remove(index);
            test.open(item / N, item % N);
        }
        */
        int index;
        int open = 0;
        while (!test.percolates()) {
            index = StdRandom.uniform(N * N);
            int row = index / N;
            int col = index % N;
            if (!test.isOpen(row, col)) {
                test.open(row, col);
                open++;
            }
        }

        //test.printGrid();
        return open /  (double) (N * N);
    }
}

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

        int index;
        while (!test.percolates()) {
            int indexRow = StdRandom.uniform(N - 1);
            int indexColum = StdRandom.uniform(N - 1)
            //int col = index % N;
            test.open(indexRow, indexColum);
//            if (!test.isOpen(indexRow, indexColum)) {
//                test.open(indexRow, indexColum);
//            }
        }

        return test.numberOfOpenSites() /  (double) (N * N);
    }
}

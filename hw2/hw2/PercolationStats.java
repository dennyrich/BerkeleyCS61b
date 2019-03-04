package hw2;

import java.util.ArrayList;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    int N;
    int T;
    private double[] trialResults;
    PercolationFactory pf;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N < 0 || T < 0) {
            throw new IllegalArgumentException("number of trials and grid size must be positive");
        }
        this.N = N;
        this.T = T;
        this.pf = pf;
        trialResults = new double[T];
        for (int i = 0; i < T; i++) {
            trialResults[i] = trial(N);
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
        return mean() - 1.96 / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 / Math.sqrt(T);
    }

    private double trial(int N) {
        Percolation test = pf.make(N);

        ArrayList<Integer> available = new ArrayList<>(N * N);
        for (int i = 0; i < N * N; i++) {
            available.add(i);
        }
        int index;
        int runs = 0;
        while (!test.percolates()) {
            if (available.size() > 0) {
                index = StdRandom.uniform(available.size());
            } else {
                index = 0;
            }

            int item = available.get(index);
            available.remove(index);
            test.open(item / N, item % N);
            runs++;
        }

        //test.printGrid();
        return runs;
    }
}

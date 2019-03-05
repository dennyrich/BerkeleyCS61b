package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;  //sets dimension of grid
    private int numSquares;
    private WeightedQuickUnionUF keys;  //0 through N^2 - 1
    private WeightedQuickUnionUF percolateTracker;
    private boolean[] isOpens;
    private int numOfOpenSites;
    private boolean percolated;

    public Percolation(int N) {
        if (N < 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        numSquares = N * N;
        keys = new WeightedQuickUnionUF(numSquares);
        percolateTracker = new WeightedQuickUnionUF(numSquares);
        // unions entire top row

        //
        for (int col = 1; col < N; col++) {
            //keys.union(col, 0);
            percolateTracker.union(col, 0);
            percolateTracker.union(numSquares - N, numSquares - col); //from
        }
        isOpens = new boolean[numSquares];
        numOfOpenSites = 0;

    }

    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }
        numOfOpenSites++;
        int key = xyTo1D(row, col);
        isOpens[key] = true;

        // determine values of keys surrounding key of (row, col)
        int keyLeft, keyRight, keyAbove, keyBelow;
        if (col == 0) {
            keyLeft = -1;
        } else {
            keyLeft = key - 1;
        }
        if (col == N - 1) {
            keyRight = -1;
        } else {
            keyRight = key + 1;
        }
        if (row == 0) {
            keyAbove = -1;
        } else {
            keyAbove = key - N;
        }
        if (row == N - 1) {
            keyBelow = -1;
        } else {
            keyBelow = key + N;
        }
        int[] adjacentKeys = new int[]{keyLeft, keyRight, keyAbove, keyBelow};

        for (int i: adjacentKeys) {
            if (i >= 0 && isOpens[i]) {
                keys.union(key, i);
                percolateTracker.union(key, i);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        return isOpens[xyTo1D(row, col)];
    }

    public boolean isFull(int row, int col) {
        int key = xyTo1D(row, col);
        return isOpens[key] && percolateTracker.connected(key, 0);
    }

    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    public boolean percolates() {
        if (N == 1) {
            return isOpens[0];
        }
        return (percolateTracker.connected(0, numSquares - 1)) && numOfOpenSites > 1;
    }

    private void validate(int row, int col) {
        if (row < 0 || col < 0 || row > N || col > N) {
            throw new IllegalArgumentException();
        }
    }

    private int xyTo1D(int row, int col) {
        return row * N + col;
    }

    private void printGrid() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(getSymbol(i, j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    private String getSymbol(int row, int col) {
        int key = xyTo1D(row, col);
        if (isFull(row, col)) {
            return "%";
        } else if (!isOpens[key]) {
            return "-";
        } else {
            return "o"; //open
        }
    }

    public static void main(String[] args) {
        /*
        Percolation test = new Percolation(5);
        for (int j = 0; j < 3; j++) {
            test.open(j, 0);
        }
        test.open(2, 1);
        for (int i = 2; i < 5; i++) {
            test.open(i, 1);
        }
        System.out.println(test.percolates());
        //test.printGrid();
        */

        PercolationStats experiment = new PercolationStats(10, 30, new PercolationFactory());
        System.out.println(experiment.mean() + " " + experiment.stddev());
    }

}

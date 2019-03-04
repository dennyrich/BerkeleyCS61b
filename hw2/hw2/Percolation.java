package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;  //sets dimension of grid
    private int numSquares;
    private WeightedQuickUnionUF keys;  //0 through N^2 - 1
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
        // unions entire top row

        //
        for (int j = 1; j < N; j++) {
            keys.union(j, 0);
        }
        isOpens = new boolean[numSquares];
        numOfOpenSites = 0;

    }

    public void open(int row, int col) {
        numOfOpenSites++;
        int key = xyTo1D(row, col);
        isOpens[key] = true;

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
            }
        }
        // checks to see if opened key is on bottom and connected to unioned top row
        if (row == N - 1) {
            if (keys.connected(key, 0)) {
                percolated = true;
            }
        }
    }

    public boolean isOpen(int row, int col) {
        return isOpens[xyTo1D(row, col)];
    }

    public boolean isFull(int row, int col) {
        int key = xyTo1D(row, col);
        return isOpens[key] && keys.connected(key, 0);
    }

    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    public boolean percolates() {
        return percolated;
    }

    private void validate(int row, int col) {
        if (row < 0 || col < 0 || row > N || col > N) {
            throw new IllegalArgumentException();
        }
    }

    private int xyTo1D(int row, int col) {
        return row * N + col;
    }

    public void printGrid() {
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

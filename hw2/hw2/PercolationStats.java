package hw2;

import edu.princeton.cs.introcs.StdStats;

import java.util.Random;

public class PercolationStats {

    private Percolation p;
    private int T;
    private double thredholds[];
    private static final long SEED = 2913431;
    private static final Random RANDOM = new Random(SEED);


    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Illegal Argument!");
        }
        this.T = T;
        thredholds = new double[T];
        for (int i = 0; i < T; i += 1) {
            Percolation p = pf.make(N);

            while(!p.percolates()) {
                int randomRow = RANDOM.nextInt(N);
                int randomCol = RANDOM.nextInt(N);
                if (!p.isOpen(randomRow, randomCol)) {
                    p.open(randomRow, randomCol);
                }
            }
            thredholds[i] = (double) p.numberOfOpenSites() / N;
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thredholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        if (T == 1) {
            return Double.NaN;
        }
        return StdStats.stddev(thredholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return (mean() - (1.96 * stddev() / Math.pow(T, 0.5)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return (mean() + (1.96 * stddev() / Math.pow(T, 0.5)));
    }
}


package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.QuickFindUF;


public class Percolation {

    private WeightedQuickUnionUF per;
    private boolean[] perOpen;
    private int N;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {

        if (N < 0) {
            throw new IllegalArgumentException("N must larger than 0");
        }

        /**
         * (col, row)
         * 0(0,0)  1(1,0)  2(2,0)
         *  3(0,1)  4(1,1)  5(2,1)
         *  6(0,2)  7(1,2)  8(2,2)
         */

        /**
         * (row, col)
         * 0(0,0)  1(1,0)  2(2,0)
         *  3(0,1)  4(1,1)  5(2,1)
         *  6(0,2)  7(1,2)  8(2,2)
         */

        /** Initiate the disjoint set to implement the data structure */
        per = new WeightedQuickUnionUF(N * N);
        perOpen = new boolean[N * N];
        this.N = N;

    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isInTheBound(row, col)) {
            throw new IndexOutOfBoundsException("Out of bounds!");
        }
        int position = toPosition(row, col);
        perOpen[position] = true;

        int neighbor = toPosition(row - 1, col);

        if (isInTheBound(row - 1, col) && isOpen(row - 1, col)) {
            per.union(position, neighbor);
        }

        neighbor = toPosition(row + 1, col);
        if (isInTheBound(row + 1, col) && isOpen(row + 1, col)) {
            per.union(position, neighbor);
        }

        neighbor = toPosition(row, col - 1);
        if (isInTheBound(row, col - 1) && isOpen(row, col - 1)) {
            per.union(position, neighbor);
        }

        neighbor = toPosition(row, col + 1);
        if (isInTheBound(row, col + 1) && isOpen(row, col + 1)) {
            per.union(position, neighbor);
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!isInTheBound(row, col)) {
            throw new IndexOutOfBoundsException("Out of bounds!");
        }
        int position = toPosition(row, col);
        return perOpen[position];


    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isInTheBound(row, col)) {
            throw new IndexOutOfBoundsException("Out of bounds!");
        }

        if (!isOpen(row, col)) {
            return false;
        }

        int toCheck = toPosition(row, col);

        /** Check if the site is simultaneously connect with the sites both on top and bottom */


        for (int i = 0; i < N; i += 1) {
            int topPosition = toPosition(0, i);
            if (per.connected(toCheck, topPosition)) {
                return true;
            }
        }
        return false;

    }

    // number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < perOpen.length; i += 1) {
            if (perOpen[i]) count += 1;
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
      for (int i = 0; i < N; i += 1) {
          if (isFull(N - 1, i)) {
              return true;
          }
      }
      return false;
    }

    private int toPosition(int row, int col) {
        return row * N + col;
    }

    private boolean isInTheBound(int row, int col) {
        return !(row < 0 || row > N - 1 || col < 0 || col > N - 1);
    }

    public static void main(String[] args) {
        WeightedQuickUnionUF test = new WeightedQuickUnionUF(11);
        test.union(0, 10);
        test.union(0, 1);
        System.out.println(test.find(10));
    }

}

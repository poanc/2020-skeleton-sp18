package hw4.puzzle;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Board implements WorldState {

    private int[][] tiles;
    private int N;

    /** Constructs a board from an N-by-N array of tiles where
     tiles[i][j] = tile at row i, column j */
    public Board(int[][] tiles) {
        N = tiles[0].length;
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
               copy[i][j] = tiles[i][j];
            }
        }
        this.tiles = copy;
    }

    /** Returns value of tile at row i, column j (or 0 if blank) */
    public int tileAt(int i, int j) {
        if (i < 0 || i > N - 1 || j < 0 || j > N -1) {
            throw new IndexOutOfBoundsException("index out of bound");
        }
        return tiles[i][j];
    }

    /** Returns the board size N */
    public int size() {
        return N;
    }

    /** Returns the neighbors of the current board */
    public Iterable<WorldState> neighbors() {

        /** find the position of the zero (the start moving point) i, j
         *  swap the item with position (i - 1, j), (i + 1, j), (i, j - 1), (i, j + 1)
         *  store the Board which is swapped
         *  return the list of the Board
         */
        ArrayList<WorldState> toReturn = new ArrayList<>();
        int[] blank = find(0);

        if (blank[0] < size() - 1) {
            toReturn.add(swapTo(blank, new int[]{blank[0] + 1, blank[1]}));
        }

        if (blank[0] > 0) {
            toReturn.add(swapTo(blank, new int[]{blank[0] - 1, blank[1]}));
        }

        if (blank[1] < size() - 1) {
            toReturn.add(swapTo(blank, new int[]{blank[0], blank[1] + 1}));
        }

        if (blank[1] > 0) {
            toReturn.add(swapTo(blank, new int[]{blank[0], blank[1] - 1}));
        }

        return toReturn;
    }

    private Board swapTo(int[] blank, int[] swapAt) {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                copy[i][j] = tileAt(i, j);
            }
        }
        int temp = tileAt(swapAt[0], swapAt[1]);
        copy[blank[0]][blank[1]] = temp;
        copy[swapAt[0]][swapAt[1]] = 0;
        return new Board(copy);
    }

    private int[] find(int number) {
        int[] toReturn = new int[2];
        outerloop:
        for (int i = 0; i < size(); i += 1) {
            for (int j = 0; j < size(); j += 1) {
                if (tileAt(i, j) == number) {
                    toReturn[0] = i;
                    toReturn[1] = j;
                    break outerloop;
                }
            }
        }
        return toReturn;
    }

    public int hamming() {
        int total = 0;
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (tileAt(i, j) == 0) {
                    continue;
                }
                if (tileAt(i, j) != goalAt(i, j)) {
                    total += 1;
                }
            }
        }
        return total;
    }

    private int goalAt(int i, int j) {
        if (i < 0 || i > N - 1 || j < 0 || j > N -1) {
            throw new IndexOutOfBoundsException("index out of bound");
        }
        return i * N + j + 1;
    }


    public int manhattan() {
        int total = 0;
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (tileAt(i, j) == 0) {
                    continue;
                }
                if (tileAt(i, j) != goalAt(i, j)) {
                    int[] goalPosition = toPosition(tileAt(i, j));
                    total += Math.abs(goalPosition[0] - i);
                    total += Math.abs(goalPosition[1] - j);
                }
            }
        }
        return total;
    }

    private int[] toPosition(int number) {
        int i = (number - 1) / N;
        int j = (number - 1) % N;
        return new int[]{i, j};
    }

    /** Estimated distance to goal. This method should
     simply return the results of manhattan() when submitted to
     Gradescope. */
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    /** Returns true if this board's tile values are the same
     position as y's */
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }

        if (y.getClass() != this.getClass()) {
            return false;
        }

        Board o = (Board) y;

        if (o.size() != size()) {
            return false;
        }

        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (o.tileAt(i, j) != tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }


    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }


}

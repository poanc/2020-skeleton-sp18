package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;

    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {

        // Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()

        /**
         * enqueue the source into fringe
         * while loop: fringe isEmpty
         * dequeue the item in fringe
         * if the item is target, then return
         * if not, enqueue all the adj of the item into fringe
         */
        Queue<Integer> fringe = new Queue<>();
        fringe.enqueue(s);
        while(!fringe.isEmpty()) {
            int v = fringe.dequeue();
            marked[v] = true;
            announce();

            if (v == t) {
                targetFound = true;
            }

            if (targetFound) {
                return;
            }

            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    fringe.enqueue(w);
                }
            }
        }

    }


    @Override
    public void solve() {
        bfs();
    }
}


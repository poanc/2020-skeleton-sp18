package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {

    public int moves;
    public ArrayDeque<WorldState> solution;
    public HashMap<WorldState, Integer> estMap;


    /** Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists. */

    public Solver(WorldState initial) {

        /** iterate each node of the input graph
         *  add every neighbor of each node into min queue
         * */
        moves = 0;
        solution = new ArrayDeque<>();
        estMap = new HashMap<>();

        SearchNode init = new SearchNode(initial, 0, null);
        MinPQ<SearchNode> fringe = new MinPQ();
        SearchNode goal = null;
        fringe.insert(init);

        while(!fringe.isEmpty()) {
            SearchNode min = fringe.delMin();

            if (min.ws.isGoal()) {
                goal = min;
                break;
            }

            for (WorldState neighbor : min.ws.neighbors()) {
                if (min.prev != null && neighbor.equals(min.prev.ws)) {
                    continue;
                }
//                int e;
//                if (estMap.containsValue(neighbor)) {
//                    e = estMap.get(neighbor);
//                } else {
//                    e = neighbor.estimatedDistanceToGoal();
//                    estMap.put(neighbor, e);
//                }
                fringe.insert(new SearchNode(neighbor, min.moveSoFar + 1 , min));
            }
        }

        while(goal != null) {
            solution.addFirst(goal.ws);
            moves += 1;
            goal = goal.prev;
        }

    }

    private class SearchNode implements Comparable<SearchNode> {
        private WorldState ws;
        public int moveSoFar;
        private SearchNode prev;
        private int priority;

        public SearchNode(WorldState ws, int moveSoFar, SearchNode prev) {
            this.ws = ws;
            this.moveSoFar = moveSoFar;
            this.prev = prev;
            int estimate = ws.estimatedDistanceToGoal();
            priority = moveSoFar + estimate;
        }

        @Override
        public int compareTo(SearchNode sn) {
            return priority - sn.priority;
        }
    }

     /** Returns the minimum number of moves to solve the puzzle starting
      * at the initial WorldState */
    public int moves() {
        return moves - 1;
    }

    /** Returns a sequence of WorldStates from the initial WorldState
     * to the solution. */
    public Iterable<WorldState> solution() {
        return solution;
    }
}

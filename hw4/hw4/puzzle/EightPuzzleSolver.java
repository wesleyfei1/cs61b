package hw4.puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class EightPuzzleSolver {
    /***********************************************************************
     * Test routine for your Solver class. Uncomment and run to test
     * your basic functionality.
    **********************************************************************/
    public static void main(String[] args) {
        //In in = new In("3 0 1 2 3 4 2 5 7 8 6");
        int N = 3;
        int[][] tiles={{-1,1,3},{4,2,5},{7,8,6}};
        //System.out.println(1);
        Board initial = new Board(tiles);
        //System.out.println(1111);
        Solver solver = new Solver(initial);
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (WorldState ws : solver.solution()) {
            StdOut.println(ws);
        }
    }
}

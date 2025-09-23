package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

import java.util.LinkedList;

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
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        //marked[s]=true;
        Queue<Integer> q= new Queue<>();
        q.enqueue(s);
        announce();
        int place=s;
        while(!q.isEmpty()){
            place=q.dequeue();
            marked[place]=true;
            System.out.println(place);
            if(place==t){
                break;
            }
            for(int w:maze.adj(place)){
                if(!marked[w]){
                    edgeTo[w] = place;
                    announce();
                    distTo[w] = distTo[place] + 1;
                    q.enqueue(w);
                }
            }
        }

    }


    @Override
    public void solve() {
        bfs();
    }
}


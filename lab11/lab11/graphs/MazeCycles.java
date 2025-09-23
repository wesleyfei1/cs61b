package lab11.graphs;

import edu.princeton.cs.algs4.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    //private int s;
    //private int t;
    private boolean targetFound = false;
    private Maze maze;
    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        //s = maze.xyTo1D(sourceX, sourceY);
        //t = maze.xyTo1D(targetX, targetY);
        distTo[0] = 0;
        edgeTo[0] = 0;
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        Stack<Integer> stack=new Stack<>();
        stack.push(0);
        //marked[0]=true;
        announce();
        int place;
        while(!stack.isEmpty()){
            place=stack.pop();
            //System.out.println(stack.size());
            if(marked[place]==true){
                //System.out.println("illegal："+place);
                return;
                //break;
            }
            //System.out.println("23232");
            marked[place]=true;
            for(int w:maze.adj(place)){
                //System.out.println(w);
                if(!marked[w]){
                    edgeTo[w] = place;
                    announce();
                    distTo[w] = distTo[place] + 1;
                    stack.push(w);
                }
            }
        }
        System.out.println("This Graph doesn't contain any cycles!!!!");
    }

    // Helper methods go here
}


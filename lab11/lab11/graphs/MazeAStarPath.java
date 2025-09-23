package lab11.graphs;

import edu.princeton.cs.algs4.MinPQ;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private MinPQ<Node> pq;
    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return -1;
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }
    private class Node implements Comparable<Node> {
        public int place;
        public int moves;
        public Node(int place,int moves){
            this.place=place;
            this.moves=moves;
        }
        public int value(){
            return (Math.abs(maze.toX(place)-maze.toX(t))+Math.abs(maze.toY(place)-maze.toY(t)));
        }
        public int compareTo(Node other){
            return Integer.compare(this.value(),other.value());
        }
    }
    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        MinPQ<Node> pq=new MinPQ<>();
        Node initial=new Node(0,0);
        int moves=0;
        announce();
        System.out.println(pq.size());
        pq.insert(initial);
        while(!pq.isEmpty()){
            //System.out.println(pq.size());
            Node current=pq.delMin();
            if(current.place==t){
                System.out.println("Find the target!!!");
                return;
            }
            marked[current.place]=true;
            for(int w:maze.adj(current.place)){
                if(!marked[w]){
                    edgeTo[w] = current.place;
                    announce();
                    distTo[w] = distTo[current.place] + 1;
                    Node ne=new Node(w,current.moves+1);
                    pq.insert(ne);
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}


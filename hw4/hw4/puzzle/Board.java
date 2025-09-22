package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

import java.util.Set;

public class Board implements WorldState{

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    private int[][] board;
    private int N;
    private int[][] goal;
    public Board(int[][] tiles){
        int num=1;
        N=tiles.length;
        //System.out.println(N);
        board=new int[N+1][N+1];
        goal=new int[N+1][N+1];
        for(int row=0;row<N;row++){
            for(int col=0;col<N;col++){
                board[row][col]=-1;
                goal[row][col]=num;
                num++;
            }
        }
        goal[N-1][N-1]=-1;

        for(int row=0;row<N;row++){
            for(int col=0;col<N;col++){
                board[row][col]=tiles[row][col];
            }
        }
    }
    public int tileAt(int i,int j){
        return board[i][j];
    }
    public int size(){
        return N;
    }
    public Iterable<WorldState> neighbors(){
        Queue<WorldState> neighbors=new Queue<>();
        int hug=size();
        int bug=-1,zug=-1;
        for(int rug=0;rug<hug;rug++)
        {
            for(int tug=0;tug<hug;tug++)
            {
                //System.out.print(board[rug][tug]+" ");
                if(tileAt(rug,tug)==-1)
                {
                    //System.out.println(rug+' '+tug);
                    bug=rug;zug=tug;
                }
            }
            //System.out.println();
        }
        int[][] o=new int[hug][hug];
        for(int pug=0;pug<hug;pug++)
        {
            for(int yug=0;yug<hug;yug++)
            {
                o[pug][yug]=tileAt(pug,yug);
            }

        }
        //System.out.println(111);
        //System.out.println(bug+" "+zug);
        for(int i=0;i<hug;i++)
        {
            for(int j=0;j<hug;j++)
            {
                if(Math.abs(-bug+i)+Math.abs(j-zug)-1==0){
                    o[bug][zug]=o[i][j];
                    o[i][j]=-1;
                    //System.out.println("111111");
                    Board neighbot=new Board(o);
                    neighbors.enqueue(neighbot);
                    o[i][j]=o[bug][zug];
                    o[bug][zug]=-1;
                }
            }
        }
        return neighbors;
    }
    public int hamming(){
        int cost=0;
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                if(board[i][j]!=goal[i][j])
                {
                   cost++;
                }
            }
        }
        return cost;
    }
    private int helper_manhatten(int row,int col)
    {
        int s=board[row][col];
        int target_row,target_col;
        if(s!=-1) {
            target_row = s / N;
            target_col = s % N - 1;
        }
        else{
            target_row=N-1;
            target_col=N-1;
        }
        return Math.abs(target_row-row)+Math.abs(target_col-col);
    }
    public int manhattan(){
        int sum=0;
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                sum+=helper_manhatten(i,j);
            }
        }
        return sum;
    }
    public int estimatedDistanceToGoal(){
        return hamming();
    }
    public boolean equals(Object y){
        return (y ==this.board);
    }
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

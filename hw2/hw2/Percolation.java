package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    public int sum;
    private WeightedQuickUnionUF uf;
    private boolean[][] open;
    private int len;
    private int transform(int row,int col){
        return (row)*len+col+1;
    }
    public Percolation(int N){
        sum=0;len=N;
        open=new boolean[N][N];
        if(N<=0)
            throw new IllegalArgumentException(N+"is not a valid number");
        uf=new WeightedQuickUnionUF(N*N+2);
        for(int i=0;i<len;i++)
        {
            for(int j=0;j<len;j++)
            {
                open[i][j]=false;
            }
        }
        for(int i=0;i<N;i++){
            //System.out.println(transform(N-1,i));
            uf.union(0,transform(0,i));
            uf.union(N*N+1,transform(N-1,i));
        }
    }
    public void open(int row,int col){
        if(row<0 || row>=len || col<0 || col>=len){
            throw new IndexOutOfBoundsException("Index Out Of Bounds");
            //return null;
        }
        if(isOpen(row,col)==true)
            return;
        int[] addx={0,0,1,-1};
        int[] addy={1,-1,0,0};
        open[row][col]=true;
        if(row==(len-1)){
            uf.union(len*len+1,transform(row,col));
        }
        sum++;
        for(int j=0;j<4;j++)
        {
            int newx=row+addx[j];
            int newy=col+addy[j];
            if(newx>=0 && newx<len && newy>=0 && newy<len)
            {
                if(open[newx][newy]==true)
                {
                    uf.union(transform(newx,newy),transform(row,col));
                }

            }
        }
    }
    public boolean isOpen(int row,int col){
        if(row<0 || row>=len || col<0 || col>=len){
            throw new IndexOutOfBoundsException("Index Out Of Bounds");
            //return null;
        }
        return open[row][col];
    }
    public boolean isFull(int row,int col){
        if(row<0 || row>=len || col<0 || col>=len){
            throw new IndexOutOfBoundsException("Index Out Of Bounds");
            //return null;
        }
        boolean i;
        if (uf.connected(0, transform(row, col))) i = true;
        else i = false;

        if(open[row][col]==false) i=false;
        return i;
    }
    public int numberOfOpenSites(){
        return sum;
    }
    public boolean percolates(){
        //System.out.println(uf.find(1));
        //System.out.println(uf.find(len*len));
        return uf.connected(0,len*len+1);
    }
    /**public static void main(String[] args){

    }*/
}

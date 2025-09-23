package byog.Core;

public class UnionFind {
    private int parent[];
    private int rank[];
    public UnionFind(int n){
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<n;i++)
        {
            parent[i]=i;
            rank[i]=1;
        }
    }
    public int find(int p) {
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }
    public boolean isConnected(int x,int y){
        if(find(x)==find(y)) return true;
        return false;
    }
    public void verge(int x,int y){
        int rootX=find(x);
        int rootY=find(y);
        if(rank[rootX]>rank[rootY])
            parent[rootY]=rootX;
        else if(rank[rootX]<rank[rootY])
            parent[rootX]=rootY;
        else{
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}

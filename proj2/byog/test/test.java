package byog.test;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Random;
//import edu.princeton.cs.introcs.StdDraw;


public class test {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 50;

    private static final long SEED = 287;
    private static final Random RANDOM = new Random(SEED);
    private static int xx[],yy[],hh[],ww[];
    private static int number;
    public static class Edge{
        int x;int y;int distance;
        public Edge(int xx,int yy,int ddistance){
            this.x=xx;this.y=yy;this.distance=ddistance;
        }
    }
    public static void fillRegtangle(int x,int y,int w,int h,TETile[][] tile){
        for(int i=x;i<x+w;i++){
            for(int j=y;j<y+h;j++){
                tile[i][j]=Tileset.GRASS;
            }
        }
    }
    public static int distance(int x,int y)
    {
        int distx=0,disty=0;
        if(xx[x]>xx[y]) distx=xx[x]-(ww[y]+xx[y]);
        else if(xx[x]<xx[y]) distx=xx[y]-(ww[x]+xx[x]);

        if(yy[x]>yy[y]) disty=yy[x]-(hh[y]+yy[y]);
        else if(yy[x]<yy[y]) disty=yy[y]-(hh[x]+yy[x]);

        int dist=Math.max(distx,0)+Math.max(disty,0);
        return dist;
    }
    public static int distance(int x, int y, int num) {
        int left   = xx[num];
        int right  = xx[num] + ww[num]-1;
        int bottom = yy[num];
        int top    = yy[num] + hh[num]-1;

        int distx = 0;
        int disty = 0;

        // x方向的距离
        if (x < left) {
            distx = left - x;
        } else if (x > right) {
            distx = x - right;
        }

        // y方向的距离
        if (y < bottom) {
            disty = bottom - y;
        } else if (y > top) {
            disty = y - top;
        }

        return distx + disty;  // 曼哈顿距离
    }
    /**public static void connect(int x,int y,TETile[][] tiles){
        int direction=0;
        int[] addx={0,0,1,-1};
        int[] addy={1,-1,0,0};
        int tempx=xx[x],tempy=yy[x];
        while(distance(tempx,tempy,y)!=0)
        {
            int newx=tempx+addx[direction];
            int newy=tempy+addy[direction];
            if(distance(newx,newy,y)<=distance(tempx,tempy,y))
            {
                /**if(!(newx>xx[x] && newx<(xx[x]+ww[x]) && newy>yy[y] && newy<(yy[y]+hh[y])) && tiles[newx][newy]==Tileset.GRASS)
                {
                    direction++;
                    direction%=4;
                }
                tempx=newx;
                tempy=newy;
                tiles[tempx][tempy]=Tileset.GRASS;
            }
            else{
                direction++;
                direction%=4;
            }
        }
    }*/
    /** 辅助：把 v 限制到 [lo, hi] */
    private static int clamp(int v, int lo, int hi) {
        if (lo > hi) return lo; // 防守性：若宽高为0导致 lo>hi，直接返回 lo（不会越界）
        if (v < lo) return lo;
        if (v > hi) return hi;
        return v;
    }

    private static boolean inBounds(int x, int y, TETile[][] tiles) {
        return x >= 0 && x < tiles.length && y >= 0 && y < tiles[0].length;
    }

    /**
     * 更稳健的 connect：从房间 a 的“靠近 b 的点”连到房间 b 的“靠近 a 的点”。
     */
    public static void connect(int a, int b, TETile[][] tiles) {
        // 房间 A 边界
        int leftA   = xx[a];
        int rightA  = xx[a] + ww[a] - 1;
        int bottomA = yy[a];
        int topA    = yy[a] + hh[a] - 1;

        // 房间 B 边界
        int leftB   = xx[b];
        int rightB  = xx[b] + ww[b] - 1;
        int bottomB = yy[b];
        int topB    = yy[b] + hh[b] - 1;

        // 中心（用于选取最接近点）
        int centerBx = (leftB + rightB) / 2;
        int centerBy = (bottomB + topB) / 2;

        // 在 A 中取一个点，使它尽量靠近 B（把 B 的中心 clamp 到 A 的范围）
        int startX = clamp(centerBx, leftA, rightA);
        int startY = clamp(centerBy, bottomA, topA);

        // 在 B 中取一个点，使它尽量靠近 start（把 start clamp 到 B 的范围）
        int targetX = clamp(startX, leftB, rightB);
        int targetY = clamp(startY, bottomB, topB);

        // 防守性：如果某个房间宽高为0（没有真正填充），回退到它的左下角（或中心计算）
        if (ww[a] <= 0 || hh[a] <= 0) {
            startX = xx[a];
            startY = yy[a];
        }
        if (ww[b] <= 0 || hh[b] <= 0) {
            targetX = xx[b];
            targetY = yy[b];
        }

        // 随机决定先走 horizontal 或 vertical
        boolean horizontalFirst = RANDOM.nextBoolean();
        int curX = startX;
        int curY = startY;

        // 把起点也设为地板（以防起点原本没有填）
        if (inBounds(curX, curY, tiles)) tiles[curX][curY] = Tileset.GRASS;

        if (horizontalFirst) {
            // 先水平
            while (curX != targetX) {
                curX += (targetX > curX) ? 1 : -1;
                if (inBounds(curX, curY, tiles)) tiles[curX][curY] = Tileset.GRASS;
            }
            // 再垂直
            while (curY != targetY) {
                curY += (targetY > curY) ? 1 : -1;
                if (inBounds(curX, curY, tiles)) tiles[curX][curY] = Tileset.GRASS;
            }
        } else {
            // 先垂直
            while (curY != targetY) {
                curY += (targetY > curY) ? 1 : -1;
                if (inBounds(curX, curY, tiles)) tiles[curX][curY] = Tileset.GRASS;
            }
            // 再水平
            while (curX != targetX) {
                curX += (targetX > curX) ? 1 : -1;
                if (inBounds(curX, curY, tiles)) tiles[curX][curY] = Tileset.GRASS;
            }
        }
    }
    public static void genRectangle(TETile[][] tile){
        int num=40;
        int i=0;
        xx=new int[num];
        yy=new int[num];
        hh=new int[num];
        ww=new int[num];
        number=num;
        while(i<num){
            int x=RANDOM.nextInt(WIDTH);
            int y=RANDOM.nextInt(HEIGHT);
            int w=2+RANDOM.nextInt(6);
            int h=2+RANDOM.nextInt(5);
            if((x+w)<WIDTH&&(y+h)<HEIGHT) {

                xx[i]=x;
                yy[i]=y;
                ww[i]=w;
                hh[i]=h;
                int flag=0;
                for(int j=0;j<i;j++)
                    if(distance(i,j)<=3)
                        flag=1;
                if(flag==1)
                    continue;

                i++;
                fillRegtangle(x, y, w, h, tile);
            }
        }
    }
    public static void addBoundary(TETile[][] tiles){
        int[] xadd={1,-1,0,0};
        int[] yadd={0,0,1,-1};
        for(int x=0;x<WIDTH;x++){
            for(int y=0;y<HEIGHT;y++){
                if(tiles[x][y]==Tileset.GRASS) {
                    int count=0;
                    for (int k = 0; k < 4; k++) {
                        int newx = x + xadd[k];
                        int newy = y + yadd[k];
                        if (newx >= 0 && newx < WIDTH && newy >= 0 && newy < HEIGHT) {
                            if (tiles[newx][newy]!=Tileset.GRASS){
                                tiles[newx][newy]=Tileset.WALL;
                            }
                        }
                    }
                }
            }
        }
    }
    public static void MST(TETile[][] tiles){
        Edge[] edges=new Edge[number*number];
        int k=0;
        for(int i=0;i<number;i++)
        {
            for(int j=i+1;j<number;j++)
            {
                edges[k] = new Edge(i, j, distance(i, j));
                k++;
            }
        }
        Edge save;
        for(int i=0;i<k;i++)
        {
            for(int j=i+1;j<k;j++)
            {
                if(edges[j].distance<edges[i].distance){
                    save=edges[i];
                    edges[i]=edges[j];
                    edges[j]=save;
                }
            }
        }
        //System.out.println(edges[k-1].distance);
        UnionFind uf=new UnionFind(number);
        int num_edges=0,tmp=0;
        System.out.println(number);
        System.out.println(k);
        while (num_edges < number - 1 && tmp < k) {
            Edge e = edges[tmp++];
            if (!uf.isConnected(e.x, e.y)) {
                uf.verge(e.x, e.y);
                connect(e.x, e.y, tiles);
                num_edges++;
            }
        }
    }
    public static void fillWithNullTiles(TETile[][] tiles){
        for(int i=0;i<WIDTH;i++){
            for(int j=0;j<HEIGHT;j++){
                tiles[i][j]=Tileset.NOTHING;
            }
        }
    }
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] Tiles = new TETile[WIDTH][HEIGHT];
        fillWithNullTiles(Tiles);
        genRectangle(Tiles);
        MST(Tiles);
        addBoundary(Tiles);
        //connect(1,8,Tiles);
        ter.renderFrame(Tiles);
    }
}

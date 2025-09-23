package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private int edge;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);
    public HexWorld(int n){
        edge=n;
    }
    public int cal_layer(int layer){
        int num_layer=0;
        if(layer<1 || layer>(edge*2))
            num_layer=-1;
        else if(layer>edge)
            num_layer=edge+(2*edge-layer)*2;
        else
            num_layer=edge+(layer-1)*2;
        return num_layer;//calculate how many a do we need to draw it
    }
    public int cal_space(int layer){
        if(layer<1 || layer>(edge*2)) return -1;
        if(layer>edge) return (layer-edge-1);
        else return (edge-layer);// calculate the ' ' needed for the hexagon
    }
    public void draw(char c){
        if(c==' ') System.out.print(" ");
        else System.out.print(c);// complete the drawing task
    }

    public void addHexgon(int x,int y,TETile[][] tiles){
        int temp_x=x,temp_y=y;
        TETile temp=randomTile();
        for(int i=1;i<=2*edge;i++)
        {
            for(int j=1;j<=cal_layer(i);j++)
                tiles[y+cal_space(i)+j-1][x+i-1]=temp;
        }
    }
    public TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.MOUNTAIN;
            case 4: return Tileset.TREE;
            default: return Tileset.WALL;
        }
    }
    public void drawHexagon(TETile[][] tiles){
        addHexgon(0,20,tiles);
        addHexgon(6,20,tiles);
        addHexgon(12,20,tiles);
        addHexgon(18,20,tiles);
        addHexgon(3,15,tiles);
        addHexgon(9,15,tiles);
        addHexgon(15,15,tiles);
        addHexgon(3,25,tiles);
        addHexgon(9,25,tiles);
        addHexgon(15,25,tiles);
        addHexgon(6,30,tiles);
        addHexgon(12,30,tiles);
        addHexgon(6,10,tiles);
        addHexgon(12,10,tiles);
    }
}

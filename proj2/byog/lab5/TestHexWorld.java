package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class TestHexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);
    public static void main(String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] randomTiles = new TETile[WIDTH][HEIGHT];
        HexWorld d=new HexWorld(3);
        for(int i=0;i<WIDTH;i++){
            for(int j=0;j<HEIGHT;j++){
                randomTiles[i][j]=Tileset.NOTHING;
            }
        }
        d.drawHexagon(randomTiles);

        ter.renderFrame(randomTiles);
    }
}

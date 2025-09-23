package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public int userx;
    public int usery;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard(TETile[][] tiles) {
        char c=' ',type=' ';
        test o;
        int num=0;
        while(true){
            if(StdDraw.hasNextKeyTyped())
            {
                char now=(char)StdDraw.nextKeyTyped();
                if(c==':'&& now=='Q') break;
                else
                {
                    if(c==' ')
                    {
                        if(now=='Q') break;
                        if(now=='L')
                            o=new test(111);
                        else
                            type='a';
                    }
                    if(type=='a' && now>='0' && now<='9')
                        num=num*10+now-'0';
                    else if(type=='a')
                    {
                        move(tiles,now);
                    }
                }
                c=now;
            }
        }
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @return the 2D TETile[][] representing the state of the world
     */
    public boolean determine(TETile[][] tiles,int x,int y)
    {
        if(x>=0 && y>=0 && x<WIDTH && y<HEIGHT)
        {
            if(tiles[x][y]==Tileset.GRASS)
                return true;
        }
        return false;
    }
    public void move(TETile[][] worldState,char c){
        int newx=0,newy=0;
        if(c=='W'||c=='w'){
            newx=userx;
            newy=usery+1;
        }
        if(c=='S'||c=='s'){
            newx=userx;
            newy=usery-1;
        }
        if(c=='A'||c=='a'){
            newx=userx-1;
            newy=usery;
        }
        if(c=='D'||c=='d'){
            newx=userx+1;
            newy=usery;
        }
        if(determine(worldState,newx,newy)==true)
        {
            worldState[userx][usery]=Tileset.GRASS;
            worldState[newx][newy]=Tileset.PLAYER;
            userx=newx;
            usery=newy;
        }
        return ;
    }
    public static void drawMenu() {
        StdDraw.setCanvasSize(WIDTH * 16, HEIGHT * 16); // 调整窗口大小
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);

        StdDraw.clear(Color.BLACK);

        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.text(WIDTH / 2.0, HEIGHT / 2.0 + 5, "CS61B: The Game");

        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(WIDTH / 2.0, HEIGHT / 2.0, "New Game (N)");
        StdDraw.text(WIDTH / 2.0, HEIGHT / 2.0 - 2, "Load Game (L)");
        StdDraw.text(WIDTH / 2.0, HEIGHT / 2.0 - 4, "Quit (Q)");

        StdDraw.show();
    }
    public void drawGame(TETile[][] worldState){
        ter.initialize(WIDTH,HEIGHT);
        ter.renderFrame(worldState);

    }
    public int concatnum(String s)
    {
        int sum=0;
        for(int i=1;i<s.length();i++)
        {
            char c=s.charAt(i);
            if(!(c>='0' && c<='9')) break;
            else
            {
                sum=sum*10;
                sum+=(c-'0');
            }
        }
        return sum;
    }
    public void tackleInput(TETile[][] tiles,String s)
    {
        int i=1;
        for(i=1;i<s.length();i++)
        {
            char c=s.charAt(i);
            if(!(c>='0' && c<='9')) break;
        }
        for(;i<s.length();i++)
        {
            char c=s.charAt(i);
            move(tiles,c);
        }
    }
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        drawMenu();
        StdDraw.pause(1000);
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        char c=input.charAt(0);
        test o;
        if(c=='q')
            return null;
        if(c=='l')
            o=new test(111);
        else
            o=new test(concatnum(input));

        o.createMap(finalWorldFrame);
        for(int i=0;i<WIDTH;i++)
        {
            for(int j=0;j<HEIGHT;j++)
            {
                if(finalWorldFrame[i][j]== Tileset.PLAYER)
                {
                    userx=i;
                    usery=j;
                }
            }
        }
        tackleInput(finalWorldFrame,input);
        drawGame(finalWorldFrame);
        return finalWorldFrame;
    }
}

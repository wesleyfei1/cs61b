package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        /**if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }*/

        int seed = 1;;//Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40);
        //String s=game.solicitNCharsInput(10);
        game.startGame();
    }

    public MemoryGame(int width, int height) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
        this.rand=new Random(1);
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        String a="";
        for(int i=0;i<n;i++)
        {
            int tileNum = rand.nextInt(3);
            a += CHARACTERS[tileNum];
        }
        return a;
    }

    public void drawFrame(String s,String ti) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
        //StdDraw.clear();
        //StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        //Font font = new Font("Monaco", Font.BOLD, 30);
        //StdDraw.setFont(font);
        //StdDraw.setXscale(0, this.width);
        //StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.RED);
        StdDraw.enableDoubleBuffering();
        StdDraw.text(this.width / 2, this.height / 2, s);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(this.width/2,10,ti);
        StdDraw.line(0,15,0,this.width);
        StdDraw.show();

    }

    public void flashSequence(String letters,String ti) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        for(int i=0;i<letters.length();i++)
        {
            char ch=letters.charAt(i);
            String s=""+ch;
            drawFrame(s,ti);
            StdDraw.pause(1000);
            drawFrame("",ti);
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n,String ti) {
        //TODO: Read n letters of player input
        String s="";
        int i=0;
        while(i<n){
            if(StdDraw.hasNextKeyTyped())
            {
                i++;
                char ch=(char)StdDraw.nextKeyTyped();
                s+=ch;
                drawFrame(s,ti);
            }
        }
        return s;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        round=1;
        String tit="Round:"+round;
        while(true){
            drawFrame("",tit);
            StdDraw.pause(1000);
            gameOver=false;
            String s=generateRandomString(round);
            String user=solicitNCharsInput(round,tit);
            if(s==user){
                round++;
            }
            else {
                String message="Game Over! You made it to round:"+round;
                drawFrame(message,tit);
                StdDraw.pause(1000);
                gameOver=true;
                break;
            }
        }
        //TODO: Establish Game loop
    }

}

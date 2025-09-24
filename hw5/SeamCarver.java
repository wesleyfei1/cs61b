import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private Picture picture;
    private double[][] energy;
    public SeamCarver(Picture picture){
        this.picture=picture;

    }
    public Picture picture(){
        return picture;
    }
    public int width(){
        return picture.width();
    }
    public int height(){
        return picture.height();
    }
    public double helper_energy(int x1,int y1,int x2,int y2){

        int rgb1=picture.getRGB(x1,y1),rgb2=picture.getRGB(x2,y2);
        int red1=(rgb1>>16)&0xFF,red2=(rgb2>>16)&0xFF;
        int green1=(rgb1>>8)&0xFF,green2=(rgb2>>8)&0xFF;
        int blue1=(rgb1>>0)&0xFF,blue2=(rgb2>>0)&0xFF;
        double s=(red1-red2)*(red1-red2)+(green1-green2)*(green1-green2)+(blue1-blue2)*(blue1-blue2);

        return s;
    }
    public int mode(int a,int b){
        return (a%b+b)%b;
    }
    public double energy(int x,int y){

        int x_right=mode(x+1,width());
        int x_left=mode(x-1,width());
        int y_up=mode(y+1,height());
        int y_down=mode(y-1,height());
        //System.out.println(x_right+","+x_left+","+y_up+","+y_down);
        return helper_energy(x_left,y,x_right,y)+helper_energy(x,y_up,x,y_down);
    }
    public double compare_min(double a,double b)
    {
        if(a<b) return a;
        return b;
    }
    public boolean decide(int x,int y){
        if(x>=0 && x<picture.height() && y>=0 && y<picture.width()) return true;
        return false;
    }
    public int[] findHorizontalSeam(){
        double[][] M=new double[height()][width()];
        energy=new double[picture.height()][picture.width()];
        for(int row=0;row<picture.height();row++)
        {
            for(int col=0;col<picture.width();col++)
            {
                energy[row][col]=energy(col,row);
            }
        }
        //System.out.println(1);
        for(int row=0;row<height();row++)
        {
            if(row==0){
                for(int col=0;col<width();col++)
                {
                    M[row][col]=energy[row][col];
                }
            }
            else {
                for(int col=0;col<width();col++)
                {
                    if(col==0) M[row][col]=energy[row][col]+compare_min(M[row-1][col],M[row-1][col+1]);
                    else if(col==width()-1) M[row][col]=energy[row][col]+compare_min(M[row-1][col],M[row-1][col-1]);
                    else M[row][col]=energy[row][col]+compare_min(M[row-1][col],compare_min(M[row-1][col-1],M[row-1][col+1]));
                }
            }
        }
       /** System.out.println(1);
        for(int i=0;i<height();i++)
        {
            for(int j=0;j<width();j++)
            {
                System.out.print(M[i][j]+" ");
            }
            System.out.println();
        }*/
        int cur_col=-1;
        double price=Integer.MAX_VALUE;
        for(int col=0;col<width();col++)
        {
            if(M[height()-1][col]<=price){
                price=M[height()-1][col];
                cur_col=col;
            }
        }
       // System.out.println(cur_col);
        int[] pre_index=new int[height()];
        for(int row=height()-1;row>=0;row--)
        {
            pre_index[row]=cur_col;
            if(row==0) break;
            double last_price=M[row][cur_col]-energy[row][cur_col];
            if(decide(row-1,cur_col+1)==true && M[row-1][cur_col+1]==last_price)
                cur_col=cur_col+1;
            else if(decide(row-1,cur_col-1)==true && M[row-1][cur_col-1]==last_price)
                cur_col=cur_col-1;
            //System.out.println(pre_index[row]);
        }
        return pre_index;
    }
    public int[] findVerticalSeam(){
        double[][] M=new double[height()][width()];
        energy=new double[picture.height()][picture.width()];
        for(int row=0;row<picture.height();row++)
        {
            for(int col=0;col<picture.width();col++)
            {
                energy[row][col]=energy(col,row);
            }
        }
        //System.out.println(1);
        for(int col = 0; col<width(); col++)
        {
            if(col==0){
                for(int row=0;row<height();row++)
                {
                    M[row][col]=energy[row][col];
                }
            }
            else {
                for(int row=0;row<height();row++)
                {
                    if(row==0) M[row][col]=energy[row][col]+compare_min(M[row][col-1],M[row+1][col-1]);
                    else if(row==height()-1) M[row][col]=energy[row][col]+compare_min(M[row][col-1],M[row-1][col-1]);
                    else M[row][col]=energy[row][col]+compare_min(M[row-1][col-1],compare_min(M[row][col-1],M[row+1][col-1]));
                }
            }
        }
        /**System.out.println(1);
        for(int i=0;i<height();i++)
        {
            for(int j=0;j<width();j++)
            {
                System.out.print(M[i][j]+" ");
            }
            System.out.println();
        }*/
        int cur_row=-1;
        double price=Integer.MAX_VALUE;
        for(int row=0;row<height();row++)
        {
            if(M[row][width()-1]<=price){
                price=M[row][width()-1];
                cur_row=row;
            }
        }
        //System.out.println(cur_row);
        int[] pre_index=new int[width()];
        for(int col=width()-1;col>=0;col--)
        {
            pre_index[col]=cur_row;
            if(col==0) break;
            double last_price=M[cur_row][col]-energy[cur_row][col];
            if(decide(cur_row+1,col-1)==true && M[cur_row+1][col-1]==last_price)
                cur_row=cur_row+1;
            else if(decide(cur_row-1,col-1)==true && M[cur_row-1][col-1]==last_price)
                cur_row=cur_row-1;
            //System.out.println(pre_index[row]);
        }
        return pre_index;
    }
    public void removeHorizontalSeam(int[] seam){

    }
    public void removeVerticalSeam(int[] seam){

    }
}

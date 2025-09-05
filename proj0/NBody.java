public class NBody{
    public static final double G=6.67e-11;
    public static double readRadius(String fileName){
        In in=new In(fileName);
        int numberOfPlanets=in.readInt();
        double radius=in.readDouble();
        return radius;
    }
    public static Planet[] readPlanets(String fileName){
        In in=new In(fileName);
        int numberOfPlanets=in.readInt();
        double radius=in.readDouble();
        Planet[] allPlanets=new Planet[numberOfPlanets];
        for(int i=0;i<numberOfPlanets;i++){
            double xP=in.readDouble();
            double yP=in.readDouble();
            double xV=in.readDouble();
            double yV=in.readDouble();
            double m=in.readDouble();
            String img=in.readString();
            Planet p=new Planet(xP,yP,xV,yV,m,img);
            allPlanets[i]=p;
        }
        return allPlanets;
    }
    public static void main(String[] args){
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        //Double.parseDouble(str)可以将string变成double
        int numberOfPlanets;
        double radius;
        Planet[] allPlanets;
        radius=NBody.readRadius(filename);
        allPlanets=NBody.readPlanets(filename);
        numberOfPlanets=allPlanets.length;
        StdDraw.setScale(-radius,radius);
        StdDraw.enableDoubleBuffering();
        
        double t=0;
        while(t<T){
            double[] xForces=new double[numberOfPlanets];
            double[] yForces=new double[numberOfPlanets];
            for(int i=0;i<numberOfPlanets;i++){
                xForces[i]=allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i]=allPlanets[i].calcNetForceExertedByY(allPlanets);
            }
            
            for(int i=0;i<numberOfPlanets;i++){
                allPlanets[i].update(dt,xForces[i],yForces[i]);
            }
            t=t+dt;
            StdDraw.clear();
            StdDraw.picture(0,0,"./images/starfield.jpg");
            for(int i=0;i<numberOfPlanets;i++){
                allPlanets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(1);
        }
        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                          allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
        }
    }
    //在static method中，无法直接访问这个class中间的数据成员

}
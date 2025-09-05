public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public static final double G=6.67e-11;
    // G in this class && Planet.G in other class
    public String imgFileName;
    public Planet(double xP,double yP,double xV, double yV,double m,String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet target){
        double dx=target.xxPos-this.xxPos;
        double dy=target.yyPos-this.yyPos;
        double r=Math.sqrt(dx*dx+dy*dy);
        return r;
    }   //because the two methods have different parameters, java can distinguish them
    //derive翻译，推导
    //Math.pow is slower than  * only
    public double calcForceExertedBy(Planet target){
        double r=calcDistance(target);
        double F=G*mass*target.mass/(r*r);
        return F;
    }
    public double calcForceExertedByX(Planet target){
        double F=calcForceExertedBy(target);
        double dx=target.xxPos-this.xxPos;
        double r=calcDistance(target);
        double F_x=F*dx/r;
        return F_x;
    }
    public double calcForceExertedByY(Planet target){
        double F=calcForceExertedBy(target);
        double dy=target.yyPos-this.yyPos;
        double r=calcDistance(target);
        double F_y=F*dy/r;
        return F_y;
    }
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double netForceX=0;
        for (Planet p:allPlanets){
            if(this.equals(p)){
                continue;
            }
            else{
                netForceX+=calcForceExertedByX(p);
            }
        }
        return netForceX;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets){
        double netForceY=0;
        for (Planet p:allPlanets){
            if(this.equals(p)){
                continue;
            }
            else{
                netForceY+=calcForceExertedByY(p);
            }
        }
        return netForceY;
    }
    public void update(double dt,double fX,double FY){
        double aX=fX/mass;
        double aY=FY/mass;
        xxVel+=dt*aX;
        yyVel+=dt*aY;
        xxPos+=dt*xxVel;
        yyPos+=dt*yyVel;
    }
    public void draw(){
        StdDraw.picture(xxPos,yyPos,"./images/"+imgFileName);
    }
}
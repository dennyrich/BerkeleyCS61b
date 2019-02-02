public class BodyExtreme {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  public Body basket;

  public BodyExtreme(double xP, double yP, double xV,double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
    basket = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName)
  }
  public void launchBasket(double xxVel, double yyVel){

  }

}

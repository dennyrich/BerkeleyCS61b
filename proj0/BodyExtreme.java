import java.lang.Math;
public class BodyExtreme {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  private static final double G = 6.67e-11;

  public BodyExtreme(double xP, double yP, double xV,double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public BodyExtreme(BodyExtreme b) {
    xxPos = b.xxPos;
    yyPos = b.yyPos;
    xxVel = b.xxVel;
    yyVel = b.yyVel;
    mass = b.mass;
    imgFileName = b.imgFileName;
  }

  public double calcDistance(BodyExtreme b) {
    /*
    Find the horizontal distance and then the
    vertical distance
    **/
    double xxDist = b.xxPos - xxPos;
    double yyDist = b.yyPos - yyPos;
    return Math.sqrt(xxDist * xxDist + yyDist * yyDist);
  }
  public double calcForceExertedBy(BodyExtreme b) {
    double dist = calcDistance(b);
    return G * (mass * b.mass) / (dist * dist);
  }

  public double calcForceExertedByX(BodyExtreme b) {
    double xxDist = b.xxPos - xxPos;
    return calcForceExertedBy(b) * (xxDist / calcDistance(b));
  }

  public double calcForceExertedByY(BodyExtreme b) {
    double yyDist = b.yyPos - yyPos;
    return calcForceExertedBy(b) * (yyDist / calcDistance(b));
  }

  public double calcNetForceExertedByX(BodyExtreme[] bodies) {
    double netForce = 0;
    for (BodyExtreme b: bodies) {
      if (! b.equals(this)) {
        netForce += calcForceExertedByX(b);
      }
    }
    return netForce;
  }

  public double calcNetForceExertedByY(BodyExtreme[] bodies) {
    double netForce = 0;
    for (BodyExtreme b: bodies) {
      if (! b.equals(this)) {
        netForce += calcForceExertedByY(b);
      }
    }
    return netForce;
  }
  // direction

  /* shipX is either -1 for left or 1 for right; same for shipY
   */
  public void update(double dt, double fX, double fY, int shipX, int shipY) {
    double xxAcc = fX/mass;
    double yyAcc = fY/mass;
    xxVel += xxAcc * dt;
    yyVel += yyAcc * dt;
    if (shipX != 0) {
      xxVel = shipX * 4e4;
    }
    if (shipY != 0) {
      yyVel = shipY * 4e4;
    }
    xxPos += xxVel * dt;
    yyPos += yyVel * dt;
  }
  public void draw() {
    StdDraw.picture(xxPos, yyPos, "/images/" + imgFileName);
  }
}
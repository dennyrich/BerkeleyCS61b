import java.lang.Math;
public class Body {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  private static final double G = 6.67e-11;

  public Body(double xP, double yP, double xV,double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Body(Body b) {
    xxPos = b.xxPos;
    yyPos = b.yyPos;
    xxVel = b.xxVel;
    yyVel = b.yyVel;
    mass = b.mass;
    imgFileName = b.imgFileName;
  }

  public double calcDistance(Body b) {
    /*
    Find the horizontal distance and then the
    vertical distance
    **/
    double xxDist = b.xxPos - xxPos;
    double yyDist = b.yyPos - yyPos;
    return Math.sqrt(xxDist * xxDist + yyDist * yyDist);
  }
  public double calcForceExertedBy(Body b) {
    double dist = calcDistance(b);
    return G * (mass * b.mass) / (dist * dist);
  }

  public double calcForceExertedByX(Body b) {
    double xxDist = b.xxPos - xxPos;
    return calcForceExertedBy(b) * (xxDist / calcDistance(b));
  }

  public double calcForceExertedByY(Body b) {
    double yyDist = b.yyPos - yyPos;
    return calcForceExertedBy(b) * (yyDist / calcDistance(b));
  }

  public double calcNetForceExertedByX(Body[] bodies) {
    double netForce = 0;
    for (Body b: bodies) {
      if (! b.equals(this)) {
        netForce += calcForceExertedByX(b);
      }
    }
    return netForce;
  }

  public double calcNetForceExertedByY(Body[] bodies) {
    double netForce = 0;
    for (Body b: bodies) {
      if (! b.equals(this)) {
        netForce += calcForceExertedByY(b);
      }
    }
    return netForce;
  }

  public void update(double dt, double fX, double fY) {
    double xxAcc = fX/mass;
    double yyAcc = fY/mass;
    xxVel += xxAcc * dt;
    yyVel += yyAcc * dt;
    xxPos += xxVel * dt;
    yyPos += yyVel * dt;
  }
  public void draw() {
    StdDraw.picture(xxPos, yyPos, imgFileName);
  }
}

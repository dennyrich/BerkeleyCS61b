public class AcornExtreme {
  double xxPos;
  double yyPos;
  double velMag = 1e5;
  double vel;

  // direction is either right or left (-1 or 1)
  public AcornExtreme(double xxPos, double yyPos, double dir, double dt) {
    this.xxPos = xxPos;
    this.yyPos = yyPos;
    this.vel = velMag * dir;
  }
  public void update(double dt) {
    xxPos += vel * dt;
  }
  public void draw() {
    StdDraw.picture(xxPos, yyPos, "/images/acorn3.gif");
  }
}

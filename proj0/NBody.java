public class NBody {
  public static double readRadius(String file) {
    In in = new In(file);
    int numPlanets = in.readInt();
    return in.readDouble();
  }
  public static Body[] readBodies(String file) {
    Body[] bodies = new Body[5];
    In in = new In(file);
    int numPlanets = in.readInt();
    double radius = in.readDouble();
    int index = 0;

    while (index < 5) {
      double xxPos = in.readDouble();
      double yyPos = in.readDouble();
      double xxVel = in.readDouble();
      double yyVel = in.readDouble();
      double mass = in.readDouble();
      String name = in.readString();
      bodies[index] = new Body(xxPos, yyPos, xxVel, yyVel, mass, name);
      index += 1;
    }
    return bodies;
  }
  public static void main(String[] args) {

    StdDraw.enableDoubleBuffering();

    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String fileName = args[2];
    double radius = readRadius(fileName);
    Body[] bodies = readBodies(fileName);
    StdDraw.setScale(-1 * radius, radius);
    StdDraw.picture(0, 0, "/images/starfield.jpg");
    for (Body b: bodies) {
      b.draw();
    }
    double time = 0;
    //stores each planet's net x and y force
    /*
    double[] netXs = new double[5];
    double[] netYs = new double[5];
    */

    double[] xForces = new double[5];
    double[] yForces = new double[5];

    while (time < T) {
      StdDraw.clear();
      /*this for loop creates an array of readBodies
      that does not include itself for the calcNetForceExertedByX
      and calcForceExertedByY methods */

      for (int i = 0; i < 5; i ++) {
        Body[] others = new Body[4];
        int index = 0;
        for (int j = 0; j < 5; j ++) {
          if (j != i) {
            others[index] = bodies[j];
            index ++;
          }
        }
        xForces[i] = bodies[i].calcNetForceExertedByX(others);
        yForces[i] = bodies[i].calcNetForceExertedByY(others);
      }

      for (int i = 0; i < 5; i ++) {
        bodies[i].update(dt, xForces[i], yForces[i]);
      }

      StdDraw.picture(0, 0, "/images/starfield.jpg");
      for (Body b: bodies) {
        b.draw();
      }
      StdDraw.show();
      StdDraw.pause(10);
      time += dt;
    }

    //printing final state
    StdOut.printf("%d\n", bodies.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < bodies.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                       bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                       bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
    }
  }
}

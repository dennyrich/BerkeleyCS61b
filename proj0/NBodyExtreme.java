public class NBodyExtreme {
  public static double readRadius(String file) {
    In in = new In(file);
    in.readInt();
    return in.readDouble();
  }
  //returns list of bodies, including space ship
  public static BodyExtreme[] readBodies(String file) {

    In in = new In(file);
    int numPlanets = in.readInt() + 1;
    double radius = in.readDouble();
    int index = 0;
    BodyExtreme[] bodies = new BodyExtreme[numPlanets];

    while (index < numPlanets - 1) { //does not include spaceship
      double xxPos = in.readDouble();
      double yyPos = in.readDouble();
      double xxVel = in.readDouble();
      double yyVel = in.readDouble();
      double mass = in.readDouble();
      String name = in.readString();
      bodies[index] = new BodyExtreme(xxPos, yyPos, xxVel, yyVel, mass, name);
      index += 1;
    }
    bodies[numPlanets - 1] = new BodyExtreme(2.27e+11, 0, 0, 2e+04, 6.4e+23,
                                            "ninjasquirrel_red.gif");
    return bodies;
  }
  public static void main(String[] args) {


    StdDraw.enableDoubleBuffering();

    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String fileName = args[2];
    double radius = readRadius(fileName);
    BodyExtreme[] bodies = readBodies(fileName);


    In in = new In(fileName);
    int numPlanets = in.readInt() + 1; //include spaceship

    StdDraw.setScale(-1 * radius, radius);
    StdDraw.picture(0, 0, "/images/starfield.jpg");
    for (BodyExtreme b: bodies) {
      b.draw();
    }
    double time = 0;
    //stores each planet's net x and y force
    /*
    double[] netXs = new double[5];
    double[] netYs = new double[5];
    */

    double[] xForces = new double[numPlanets];
    double[] yForces = new double[numPlanets];
    int shipX;
    int shipY;
    AcornExtreme acorn = null;

    while (time < T) {
      shipX = 0;
      shipY = 0;
      StdDraw.clear();
      /*this for loop creates an array of readBodies
      that does not include itself for the calcNetForceExertedByX
      and calcForceExertedByY methods */

      for (int i = 0; i < numPlanets; i ++) {
        BodyExtreme[] others = new BodyExtreme[numPlanets - 1];
        int index = 0;
        for (int j = 0; j < numPlanets; j ++) {
          if (j != i) {
            others[index] = bodies[j];
            index ++;
          }
        }
        xForces[i] = bodies[i].calcNetForceExertedByX(others);
        yForces[i] = bodies[i].calcNetForceExertedByY(others);
      }

      /*this determines if the spaceship should move in a direction, based on
      input from the arrow keys */
      for (int i = 0; i < numPlanets - 1; i ++) {
        bodies[i].update(dt, xForces[i], yForces[i], 0, 0);
      }
      // each integer corresponds to an arrow key
      if (StdDraw.isKeyPressed(68)) {
        shipX = 1;
      } else if (StdDraw.isKeyPressed(65)) {
        shipX = -1;
      }
      if (StdDraw.isKeyPressed(87)) {
        shipY = 1;
      } else if (StdDraw.isKeyPressed(83)) {
        shipY = -1;
      }
      if (StdDraw.isKeyPressed(78)) { //letter N

        acorn = new AcornExtreme(bodies[numPlanets - 1].xxPos,
                                  bodies[numPlanets - 1].yyPos, -1, dt);
      } else if (StdDraw.isKeyPressed(77)) { //letter M
        acorn = new AcornExtreme(bodies[numPlanets - 1].xxPos,
                                  bodies[numPlanets - 1].yyPos, 1, dt);
      }
      //update the spaceship separately after getting key from keyboard input
      bodies[numPlanets - 1].update(dt, xForces[numPlanets - 1],
                              yForces[numPlanets - 1], shipX, shipY);

      StdDraw.picture(0, 0, "/images/starfield.jpg");

      // do all this only if there is an acorn
      if (acorn != null) {
        acorn.update(dt);
        acorn.draw();
        for (int i = 0; i < numPlanets - 1; i ++) {
          if (Math.abs(bodies[i].xxPos - acorn.xxPos) < 1e10) {
            if (Math.abs(bodies[i].yyPos - acorn.yyPos) < 1e10) {
              bodies[i].imgFileName = "squirrel.gif";
            }
          }
        }
      }

      for (BodyExtreme b: bodies) {
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

package bearmaps;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import java.util.LinkedList;


public class KDTreeTest {
    @Test
    public void testNaive() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        double x = ret.getX(); // evaluates to 3.3
        double y = ret.getY(); // evaluates to 4.4
        assertEquals(x, 3.3, 0.01);
        assertEquals(y, 4.4, 0.01);
    }

    @Test
    public void testBasicConstructor() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);
        Point p4 = new Point(-36, 22);

        KDTree nn = new KDTree(List.of(p1, p2, p3, p4));
    }

    @Test
    public void testBasic() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        KDTree nn = new KDTree(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        double x = ret.getX(); // evaluates to 3.3
        double y = ret.getY(); // evaluates to 4.4
        assertEquals(3.3, x, 0.01);
        assertEquals(4.4, y, 0.01);
    }

    @Test
    public void compareToNaive() {
        double xCoord;
        double yCoord;
        PointSet[] tests = makeRandomKDAndNaive();
        PointSet kdTest = tests[0];
        PointSet naive = tests[1];
        for (int i = 0; i < 1000; i++) {
            xCoord = (Math.random() - 0.5) * 100;
            yCoord = (Math.random() - 0.5) * 100;
            System.out.println("Point: (" + xCoord + ", " + yCoord + ")");
            assertEquals(naive.nearest(xCoord, yCoord), kdTest.nearest(xCoord, yCoord));
        }
    }

//    @Test
//    public void testTreeStructure() {
//        PointSet test = makeRandomKDTree();
//    }

    @Test
    public void timeTesting() {
        List<Integer> ns = List.of(100, 1000, 10000, 100000, 500000);
        for (int N : ns) {
            printTimeElapsedKd(N, 10000);
            printTimeElapsedNaive(N, 10000);
        }
    }

    private List<Point> makeRandomPoints(int N) {
        List<Point> setOfPoints = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            double randomX = (Math.random() - 0.5) * 100;
            double randomY = (Math.random() - 0.5) * 100;
            setOfPoints.add(new Point(randomX, randomY));
            //System.out.println("Point: (" + randomX + ", " + randomY + ")");
        }
        return setOfPoints;
    }
    private void printTimeElapsedKd(int N, int numQuery) {
        List<Point> pointSet = makeRandomPoints(N);
        KDTree timeKdTree = new KDTree(pointSet);
        Stopwatch timer = new Stopwatch();
        List<Point> targets = makeRandomPoints(numQuery);
        for (Point p : targets) {
            Point nearest = timeKdTree.nearest(p.getX(), p.getY());
        }
        System.out.println("(KDTree) Point count: " + N + "; Query count: " + numQuery
                            + "; Time elapsed: " + timer.elapsedTime());
    }

    private void printTimeElapsedNaive(int N, int numQuery) {
        List<Point> pointSet = makeRandomPoints(N);
        NaivePointSet timeNaive = new NaivePointSet(pointSet);
        Stopwatch timer = new Stopwatch();
        List<Point> targets = makeRandomPoints(numQuery);
        for (Point p : targets) {
            Point nearest = timeNaive.nearest(p.getX(), p.getY());
        }
        System.out.println("(Naive ) Point count: " + N + "; Query count: " + numQuery
                            + "; Time elapsed: " + timer.elapsedTime());
    }

    private PointSet[] makeRandomKDAndNaive() {
        List<Point> setOfPoints = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            double randomX = (Math.random() - 0.5) * 100;
            double randomY = (Math.random() - 0.5) * 100;
            setOfPoints.add(new Point(randomX, randomY));
            //System.out.println("Point: (" + randomX + ", " + randomY + ")");
        }
        return new PointSet[]{new KDTree(setOfPoints), new NaivePointSet(setOfPoints)};
    }

    private PointSet makeRandomKDTree() {
        List<Point> setOfPoints = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            double randomX = (Math.random() - 0.5) * 100;
            double randomY = (Math.random() - 0.5) * 100;
            setOfPoints.add(new Point(randomX, randomY));
            //System.out.println("Point: (" + randomX + ", " + randomY + ")");
        }
        return new KDTree(setOfPoints);
    }
    /**
     * @Source- CS61b lecture videos, Project 2b video (Josh Hug)
     */
}

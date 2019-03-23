package bearmaps;
import org.junit.Test;

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
        List<Point> setOfPoints = new LinkedList<>();
        double xCoord = (Math.random() - 0.5) * 100;
        double yCoord = (Math.random() - 0.5) * 100;
        PointSet[] tests = makeRandomKDAndNaive();
        PointSet kdTest = tests[0];
        PointSet naive = tests[1];
        assertEquals(naive.nearest(xCoord, yCoord), kdTest.nearest(xCoord, yCoord));
    }

    @Test
    public void testTreeStructure() {
        PointSet test = makeRandomKDTree();
    }


    private PointSet[] makeRandomKDAndNaive() {
        List<Point> setOfPoints = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
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

}

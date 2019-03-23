package bearmaps;
import java.util.ArrayList;
import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Node> nodes;

    public NaivePointSet(List<Point> points) {
        nodes = new ArrayList<>();
        for (Point p : points) {
            this.nodes.add(new Node(p));
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Point target = new Point(x, y);
        Node closest = nodes.get(0);

        for (int i = 1; i < nodes.size(); i++) {
            closest = Node.closer(target, closest, nodes.get(i));
        }
        return closest.point;
    }

    private static class Node {
        Point point;

        private Node(Point p) {
            this.point = p;
        }
        private static Node closer(Point target, Node one, Node two) {
            Point o = one.point;
            Point t = two.point;
            if (Point.distance(target, o) > Point.distance(target, t)) {
                return two;
            }
            return one;
        }
    }
}

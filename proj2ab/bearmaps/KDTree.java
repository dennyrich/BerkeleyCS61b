package bearmaps;

import java.util.List;
public class KDTree implements PointSet {

    private Node root;

    public KDTree(List<Point> points) {
        root = new Node(points.get(0));
        root.usingXAxis = true;
        int size = points.size();
        for (int i = 1; i < size; i++) {
            Node newNode = new Node(points.get(i));
            insert(newNode, root);
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Node closest = nearestInner(root, new Point(x, y), root);
        return closest.point;
    }

    private Node nearestInner(Node current, Point target, Node closest) {
        if (current == null) {
            return closest;
        }
        if (Point.distance(current.point, target) < Point.distance(closest.point, target)) {
            closest = current;
        }
        Node goodSide;
        Node badSide;
        Node targetNode = new Node(target);
        if (current.compareTo(targetNode) > 0) {
            goodSide = current.leftOrDown;
            badSide = current.rightOrUp;
        } else {
            goodSide = current.rightOrUp;
            badSide = current.leftOrDown;
        }
        double distanceToBadSide;

        closest = nearestInner(goodSide, target, closest);

        if (current.usingXAxis) {
            distanceToBadSide = Math.abs(target.getX() - current.point.getX());
        } else {
            distanceToBadSide = Math.abs(target.getY() - current.point.getY());
        }
        if (Math.pow(distanceToBadSide, 2) <= Point.distance(target, closest.point)) {
            closest = nearestInner(badSide, target, closest);
        }
        return closest;
    }

    private void insert(Node child, Node parent) {
        if (parent.compareTo(child) < 0) {
            if (parent.rightOrUp == null) {
                parent.rightOrUp = child;
                child.usingXAxis = !parent.usingXAxis;
            } else {
                insert(child, parent.rightOrUp);
            }
        } else {
            if (parent.leftOrDown == null) {
                parent.leftOrDown = child;
                child.usingXAxis = !parent.usingXAxis;
            } else {
                insert(child, parent.leftOrDown);
            }
        }
    }



    /**
     * *****************************************************************
     */
    private static class Node implements Comparable<Node> {
        Point point;
        boolean usingXAxis;
        Node leftOrDown;
        Node rightOrUp;

        private Node(Point p) {
            this.point = p;
        }

        @Override
        public int compareTo(Node other) {
            if (point.getX() == other.point.getX() && point.getY() == other.point.getY()) {
                return 0;
            }
            double comparison;
            if (usingXAxis) {
                comparison =  point.getX() - other.point.getX();
            } else {
                comparison =  point.getY() - other.point.getY();
            }
            if (comparison > 0) {
                return 1;
            } else {
                return -1;
            }
        }
    }
    /**
     * @source- CS61b lecture video, Josh Hug
     */
}

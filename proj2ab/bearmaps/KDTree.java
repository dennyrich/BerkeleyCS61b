package bearmaps;

import java.util.ArrayList;
import java.util.List;
public class KDTree implements PointSet{
    private List<Node> nodes;
    private Node root;
    private int size;

    public KDTree(List<Point> points) {
        nodes = new ArrayList<>();
        root = new Node(points.get(0));
        root.usingXAxis = true;
        size = points.size();
        for (int i = 1; i < size; i++) {
            Node newNode = new Node(points.get(i));
            nodes.add(newNode);
            insert(newNode, root);
        }
        // this will place all of the following nodes -"chain reaction"
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
        if (closest.compareTo(current) < 0) {
            goodSide = current.leftOrDown;
            badSide = current.rightOrUp;
        } else {
            goodSide = current.rightOrUp;
            badSide = current.leftOrDown;
        }
        double distanceToBadSide;
        if (current.usingXAxis) {
            distanceToBadSide = Math.abs(target.getX() - current.point.getX());
        } else {
            distanceToBadSide = Math.abs(target.getY() - current.point.getY());
        }
        closest = nearestInner(goodSide, target, closest);
        if (distanceToBadSide < Point.distance(target, closest.point)) {
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
        }
        // else if parent.compareTo(child) > 0
        else {
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
}

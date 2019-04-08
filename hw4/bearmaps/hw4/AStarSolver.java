package bearmaps.hw4;

import bearmaps.hw4.lectureexample.WeightedDirectedGraph;
import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;
//import java.util.LinkedList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private AStarGraph<Vertex> input;
    private HashMap<Vertex, Double> distTo;
    private HashMap<Vertex, Vertex> pathTo;
    private DoubleMapPQ<Vertex> pq;
    private Vertex end;
    /**
     * these ones are for the required methods
     */
    private double timeElapsed;
    //private double timeout;
    private int numStatesExplored;
    private double solutionWeight;
    private LinkedList<Vertex> solution;
    private SolverOutcome outcome;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();

        distTo = new HashMap<>();
        pathTo = new HashMap<>();

        timeElapsed = 0;
        //accounting for adding the source initially
        this.input = input;
        this.end = end;
        //this.timeout = timeout;
        numStatesExplored = 1;
        solutionWeight = 0;
        pq = new DoubleMapPQ<>();
        solution = new LinkedList<>();

        setAllToInfinity(start);


        pq.add(start, input.estimatedDistanceToGoal(start, end));
        distTo.put(start, 0.0);
        Vertex best = start;
        while (pq.size() > 0 && timeElapsed <= timeout && best != end) {
            best = pq.removeSmallest();

            if (pq == null && best.equals(end)) {
                outcome = SolverOutcome.UNSOLVABLE;
                return;
            }

            List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(best);
            for (WeightedEdge<Vertex> e : neighborEdges) {
                relax(e);
            }

            timeElapsed = sw.elapsedTime();
        }
        //case of unsolvable should be taken care of because the constructor should "return"
        if (timeElapsed > timeout) {
            outcome =  SolverOutcome.TIMEOUT;
        } else {
            Vertex pathThrough = end;
            System.out.println(end);
            solution.add(end);
            System.out.println(solution);
            while (!pathThrough.equals(start)) {
                pathThrough = pathTo.get(pathThrough);
                solution.addFirst(pathThrough);
            }
        }
    }
    public SolverOutcome outcome() {
        return outcome;
    }

    public List<Vertex> solution() {
        return solution;
    }
    public double solutionWeight() {
        return solutionWeight;
    }
    public int numStatesExplored() {
        return numStatesExplored;
    }
    public double explorationTime() {
        return timeElapsed;
    }

    // might need to change; each time, searches through pq
    private void relax(WeightedEdge<Vertex> e) {
        Vertex p = e.from();
        Vertex q = e.to();
        Double w = e.weight();
        if (!distTo.containsKey(p)) {
            distTo.put(p, Double.MAX_VALUE);
        }
        if (!distTo.containsKey(q)) {
            distTo.put(q, Double.MAX_VALUE);
        }
        if (distTo.get(p) + w < distTo.get(q)) {
            pathTo.put(q, p);
            distTo.put(q, distTo.get(p) + w);
            if (pq.contains(q)) {
                pq.changePriority(q, distTo.get(q) +
                                    input.estimatedDistanceToGoal(q, end));
            } else {
                pq.add(q, distTo.get(q) +
                        input.estimatedDistanceToGoal(q, end));
            }
        }
    }

    private void setAllToInfinity(Vertex v) {
        LinkedList<Vertex> queue = new LinkedList<>();
        queue.addLast(v);

    }
}

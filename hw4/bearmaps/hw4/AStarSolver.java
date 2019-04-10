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
import java.util.Set;
import java.util.HashSet;


public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private AStarGraph<Vertex> input;
    private HashMap<Vertex, Double> distTo;
    private HashMap<Vertex, WeightedEdge<Vertex>> edgeTo;
    private Set<Vertex> visited;
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
        edgeTo = new HashMap<>();
        visited = new HashSet<>();

        timeElapsed = 0;
        //accounting for adding the source initially
        this.input = input;
        this.end = end;
        numStatesExplored = 1;
        solutionWeight = 0;
        pq = new DoubleMapPQ<>();
        solution = new LinkedList<>();



        pq.add(start, input.estimatedDistanceToGoal(start, end));
        distTo.put(start, 0.0);
        Vertex best;
        boolean firstRound = true;
        List<WeightedEdge<Vertex>> neighborEdges;
        while (pq.size() > 0 && timeElapsed <= timeout) {
            best = pq.removeSmallest();

            //repeats loop until best .equals end
            if (best.equals(end)) {
                if (!edgeTo.containsKey(end)) {
                    edgeTo.put(end, new WeightedEdge<>(end, end, 0));
                }
                break;
            }

            if (best.equals(start) && !firstRound) {
                outcome = SolverOutcome.UNSOLVABLE;
                return;
            }
            firstRound = false;

            neighborEdges = input.neighbors(best);
            for (WeightedEdge<Vertex> e : neighborEdges) {
                if (!visited.contains(e.to())) {
                    visited.add(e.to());
                    if (!distTo.containsKey(e)) {
                        distTo.put(e.to(), Double.MAX_VALUE);
                    }
                    numStatesExplored++;
                    relax(e);
                }
            }

            timeElapsed = sw.elapsedTime();
        }

        if (timeElapsed > timeout) {
            outcome =  SolverOutcome.TIMEOUT;
        } else {
            WeightedEdge<Vertex> edgeTemp = edgeTo.get(end);
            solution.add(end);
            while (!edgeTemp.from().equals(start) ) {
               solutionWeight += edgeTemp.weight();
               solution.addFirst(edgeTemp.from());
               edgeTemp = edgeTo.get(edgeTemp.from());
            }
            if (edgeTemp.from().equals(start)) {
                if (!start.equals(end)) {
                    solution.addFirst(edgeTemp.from());
                }
                outcome = SolverOutcome.SOLVED;
            } else {
                outcome = SolverOutcome.UNSOLVABLE;
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

        if (distTo.get(p) + w < distTo.get(q)) {
            edgeTo.put(q, e);
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
}

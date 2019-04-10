package bearmaps.hw4;

import bearmaps.hw4.lectureexample.WeightedDirectedGraph;
import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;


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
        visited.add(start);

        timeElapsed = 0;
        //accounting for adding the source initially
        this.input = input;
        this.end = end;
        numStatesExplored = 0;
        solutionWeight = 0;
        pq = new DoubleMapPQ<>();
        solution = new LinkedList<>();
        outcome = SolverOutcome.SOLVED;



        pq.add(start, input.estimatedDistanceToGoal(start, end));
        distTo.put(start, 0.0);
        Vertex best = start;

        if (start.equals(end)) {
            solution.add(start);
            solutionWeight = 0;
            numStatesExplored = 1;
            outcome = SolverOutcome.SOLVED;
            timeElapsed = sw.elapsedTime();
            return;
        }

        while (pq.size() > 0 && timeElapsed <= timeout && !best.equals(end)) {
            best = pq.removeSmallest();
            visited.add(best);
            numStatesExplored++;

            for (WeightedEdge<Vertex> e : input.neighbors(best)) {
                if (!visited.contains(e.to())) {
                    if (!distTo.containsKey(e.to())) {
                        distTo.put(e.to(), Double.MAX_VALUE);
                    }
                    relax(e);
                }
            }

            if (pq.size() == 0 && !best.equals(end)) {
                outcome = SolverOutcome.UNSOLVABLE;
            }

            timeElapsed = sw.elapsedTime();
        }

        /**
         * this part interprets the solution from the edgeTo set
         */

        if (timeElapsed > timeout) {
            outcome =  SolverOutcome.TIMEOUT;

        } else if (!outcome.equals(SolverOutcome.UNSOLVABLE)) {
            WeightedEdge<Vertex> edgeTemp = edgeTo.get(end);
            solution.add(end);
            solutionWeight += edgeTemp.weight();
            while (!edgeTemp.from().equals(start) ) {
                edgeTemp = edgeTo.get(edgeTemp.from());
                solutionWeight += edgeTemp.weight();
                solution.addFirst(edgeTemp.from());
            }
            if (edgeTemp.from().equals(start)) {
                if (!start.equals(end)) {
                    solution.addFirst(edgeTemp.from());
                }
                outcome = SolverOutcome.SOLVED;
            }
            timeElapsed = sw.elapsedTime();
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

package creatures;

import huglife.*;
import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {
    private static final int RED = 34;
    private static final int GREEN = 0;
    private static final int BLUE = 231;
    //private double energy;

    private static final double ENERGY_PER_MOVE = 0.03;
    private static final double ENERGY_PER_STAY = 0.01;

    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    public Color color() {
        return color(RED, GREEN, BLUE);
    }

    @Override
    public void attack(Creature c) {
        this.energy += c.energy();
    }

    @Override
    public void move() {
        this.energy -= ENERGY_PER_MOVE;
        if (energy < 0) {
            energy = 0;
        }
    }

    @Override
    public void stay() {
        this.energy -= ENERGY_PER_STAY;
        if (energy < 0) {
            energy = 0;
        }
    }


    @Override
    public Clorus replicate() {
        energy = energy/2;
        Clorus offspring = new Clorus(energy);
        return offspring;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        boolean anyPlip = false;
        boolean noSquaresEmpty = true;

        for (Map.Entry<Direction, Occupant> i : neighbors.entrySet()) {
            if (i.getValue().name().equals("plip")) {
                plipNeighbors.addLast(i.getKey());
                anyPlip = true;
            } else if (i.getValue().name().equals("empty")) {
                emptyNeighbors.addLast(i.getKey());
                noSquaresEmpty = false;
            }
        }
        //plip will stay if all squares around it are empty
        if (noSquaresEmpty) {
            return new Action(Action.ActionType.STAY);
        }
        // clorus will attack plip randomly
        else if (anyPlip) {
            return new Action(Action.ActionType.ATTACK, HugLifeUtils.randomEntry(plipNeighbors));
        }
        //replicate to random empty square if energy >= 1
        else if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, HugLifeUtils.randomEntry(emptyNeighbors));
        }
        //move to random empty square
        return new Action(Action.ActionType.MOVE, HugLifeUtils.randomEntry(emptyNeighbors));

    }
}


package creatures;

import huglife.*;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    private static final double ENERGY_PER_MOVE = 0.15;
    private static final double ENERGY_PER_STAY = 0.2;

    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        super("plip");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        g = 63 + 96 * (int) energy;
        r = 99;
        b = 76;
        return color(r, g, b);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        energy -= ENERGY_PER_MOVE;
        checkEnergyBoundary(); //cannot go below 0 or above 2 (self defined function)
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        energy += ENERGY_PER_STAY;
        checkEnergyBoundary();
    }

    private void checkEnergyBoundary() {
        if (energy > 2) {
            energy = 2;
        } else if (energy < 0) {
            energy = 0;
        }
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        double halfEnergy = energy / 2;
        energy = halfEnergy;
        Plip offspring = new Plip(halfEnergy);

        return offspring;
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        //Deque<Direction> clorusNeighbors = new ArrayDeque<>();
        boolean anyClorus = false;
        boolean allSquaresOccupied = true;
        // TODO
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)

        for (Map.Entry<Direction, Occupant> i : neighbors.entrySet()) {
            if (i.getValue().name().equals("empty")) {
                emptyNeighbors.addLast(i.getKey());
                allSquaresOccupied = false;
            } else if (i.getValue().name().equals("clorus")) {
                anyClorus = true;
                //clorusNeighbors.addLast(i.getKey());
            }
        }

        if (allSquaresOccupied) {
            return new Action(Action.ActionType.STAY);
        }

        else if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, HugLifeUtils.randomEntry(emptyNeighbors));
        }

        else if (anyClorus) {
            double random = HugLifeUtils.random();
            if (random >= 0.5) {
                return new Action(Action.ActionType.MOVE, HugLifeUtils.randomEntry(emptyNeighbors));
            }
        }

        // Rule 4
        return new Action(Action.ActionType.STAY);
    }
}

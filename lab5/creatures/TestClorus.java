package creatures;

import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestClorus {
    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.move();
        assertEquals(1.94, c.energy(), 0.01);
        c.stay();
        assertEquals(1.93, c.energy(), 0.01);
        c.stay();
        assertEquals(1.92, c.energy(), 0.01);


    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(2.5);
        Clorus child = c.replicate();
        assertEquals(1.25, child.energy(), 0);
    }

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Plip());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // sees a plip and not "no squares empty"
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Plip());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.ATTACK, Direction.BOTTOM);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space. (assert doesn't stay)
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(allEmpty);
        Action unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);


        // Energy < 1; move and no plip.
        c = new Clorus(0.99);

        HashMap<Direction, Occupant> topEmptyNoPlip = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Impassible());
        allEmpty.put(Direction.LEFT, new Impassible());
        allEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(allEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);

        assertEquals(expected, actual);



    }
}


import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;

import info.gridworld.actor.*;
import info.gridworld.grid.*;
import jdk.nashorn.internal.runtime.arrays.NumericElements;

public class JumperTest {
    Rock rock;
    Actor actor;
    Actor actor2;
    Flower flower;
    Jumper jumper;
    Jumper jumper2;
    Jumper jumper3;
    Jumper jumper4;
    ActorWorld world;

	@Before
	public void initial() {
        rock = new Rock();
        actor = new Actor();
        actor2 = new Actor();
        flower = new Flower();
        jumper = new Jumper();
        jumper2 = new Jumper();
        jumper3 = new Jumper();
        jumper4 = new Jumper();
        world = new ActorWorld();
    }

	@After
	public void release() {}

    /**
     * Case one:
     *   If the front location is empty, but two cells away location contains 
     *   a flower or a rock
     * 
     * Wished Result:
     *   1. Called by act() or move(), Jumper moves one step if the location 
     *      in front of it is empty, but the location two cells in front contains 
     *      a flower or a rock
     *   2. Called by jump(), remove the flower or the rock and Jumper jumps there.
     */
    @Test
    public void testCaseOne() {
        // Rest the world
        reset();
        // Test Result one
        world.add(new Location(Number.TWO, Number.TWO), rock);
        world.add(new Location(Number.FOUR, Number.TWO), jumper);
        jumper.act();
        assertEquals(rock, world.getGrid().get(new Location(Number.TWO, Number.TWO)));
        assertEquals(jumper, world.getGrid().get(new Location(Number.THREE, Number.TWO)));
        // Test Result two
        world.add(new Location(Number.TWO, Number.FOUR), flower);
        world.add(new Location(Number.FOUR, Number.FOUR), jumper2);
        jumper2.jump();
        assertEquals(jumper2, world.getGrid().get(new Location(Number.TWO, Number.FOUR)));
    }
    
    /**
     * Case two:
     *   If the location two cells in front of the jumper is out of the grid
     * 
     * Wished Result:
     *   1. If called by jump() method, remove the jumper.
     *   1. If called by act() and move() methods, moves one step and leave 
     *      a flower in the previous loaction.
     */
    @Test
    public void testCaseTwo() {
        // Rest the world
        reset();
        // Test Result one
        world.add(new Location(Number.ONE, Number.TWO), jumper);
        jumper.jump();
        assertNull(world.getGrid().get(new Location(Number.ONE, Number.TWO)));
        // Test Result two
        world.add(new Location(Number.ONE, Number.FOUR), jumper2);
        jumper2.act();
        assertEquals(jumper2, world.getGrid().get(new Location(0, Number.FOUR)));
    }
    
    /**
     * Case three:
     *   If jumper is facing an edge of the grid
     * 
     * Wished Result:
     *   1. If called by move() or jump() methods, remove the jumper.
     *   1. If called by act() method, turn right by 45 degrees.
     */
    @Test
    public void testCaseThree() {
        // Rest the world
        reset();
        // Test Result one
        world.add(new Location(0, Number.TWO), jumper);
        jumper.jump();
        assertNull(world.getGrid().get(new Location(0, Number.TWO)));
        // Test Result two
        world.add(new Location(0, Number.FOUR), jumper2);
        jumper2.act();
        assertEquals(jumper2, world.getGrid().get(new Location(0, Number.FOUR)));
        assertEquals(45 ,jumper2.getDirection());
    }
    
    /**
     * Case four:
     *   If another actor (not a flower or a rock) is in the cell that is 
     *   two cells in front of the jumper.
     * 
     * Wished Result:
     *   1. If called by jump() methods, remove the actor and place the jumper there.
     *   1. If called by act() or move() methods, moves one step.
     */
    @Test
    public void testCaseFour() {
        // Rest the world
        reset();
        // Test Result one
        world.add(new Location(Number.TWO, Number.TWO), actor);
        world.add(new Location(Number.FOUR, Number.TWO), jumper);
        jumper.act();
        assertEquals(actor, world.getGrid().get(new Location(Number.TWO, Number.TWO)));
        assertEquals(jumper, world.getGrid().get(new Location(Number.THREE, Number.TWO)));
        // Test Result two
        world.add(new Location(Number.TWO, Number.FOUR), actor2);
        world.add(new Location(Number.FOUR, Number.FOUR), jumper2);
        jumper2.jump();
        assertEquals(jumper2, world.getGrid().get(new Location(Number.TWO, Number.FOUR)));
    }

    /**
     * Case five:
     *   If it encounters another jumper in its path?
     * 
     * Wished Result:
     *   1. Two ceils away
     *          1) Facing the opposite directions, one moves and another turns.
     *          2) Otherwise, both just jump;
     *   1. One ceil away
     *          both just jump;
     */
    @Test
    public void testCaseFive() {
        // Test Result one
        // Rest the world
        reset();
        // two ceils & opposite direction
        world.add(new Location(Number.FOUR, Number.TWO), jumper);
        world.add(new Location(Number.TWO, Number.TWO), jumper2);
        jumper2.setDirection(180);
        jumper2.act();
        jumper.act();
        assertEquals(45, jumper.getDirection());
        assertEquals(jumper, world.getGrid().get(new Location(Number.FOUR, Number.TWO)));
        assertEquals(jumper2, world.getGrid().get(new Location(Number.THREE, Number.TWO)));
        assertEquals(180, jumper2.getDirection());
        // two ceils & different direction
        world.add(new Location(Number.TWO, Number.FOUR), jumper3);
        world.add(new Location(Number.FOUR, Number.FOUR), jumper4);
        jumper3.act();
        jumper4.act();
        assertEquals(jumper3, world.getGrid().get(new Location(0, Number.FOUR)));
        assertEquals(jumper4, world.getGrid().get(new Location(Number.TWO, Number.FOUR)));
        
        // Test Result two
        // Rest the world
        reset();
        jumper.setDirection(0);
        jumper3.setDirection(0);
        jumper4.setDirection(0);
        // one ceil & opposite direction
        world.add(new Location(Number.FOUR, Number.TWO), jumper);
        world.add(new Location(Number.THREE, Number.TWO), jumper2);
        jumper2.setDirection(180);
        jumper2.act();
        jumper.act();
        assertEquals(0, jumper.getDirection());
        assertEquals(jumper, world.getGrid().get(new Location(Number.TWO, Number.TWO)));
        assertEquals(jumper2, world.getGrid().get(new Location(Number.FIVE, Number.TWO)));
        assertEquals(180, jumper2.getDirection());
        // one ceil & different direction
        world.add(new Location(Number.THREE, Number.FOUR), jumper3);
        world.add(new Location(Number.FOUR, Number.FOUR), jumper4);
        jumper4.act();
        jumper3.act();
        assertEquals(jumper3, world.getGrid().get(new Location(Number.ONE, Number.FOUR)));
        assertEquals(jumper4, world.getGrid().get(new Location(Number.TWO, Number.FOUR)));
    }

    public void reset() {
        for (int r = 0; r <= Number.SIX; r++) {
            for (int c = 0; c <= Number.SIX; c++) {
                world.remove(new Location(r, c));
            }
        }
    }
}

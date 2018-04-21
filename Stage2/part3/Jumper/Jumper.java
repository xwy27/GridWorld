import java.awt.Color;
import info.gridworld.grid.*;
import info.gridworld.actor.*;

/**
 * A <code>Jumper</code> is an actor that can jump and turn.
 * It jumps over rocks and flowers. <br />
 */
public class Jumper extends Actor {
    /**
     * Constract a red jumper
     */
    public Jumper() {
        setColor(Color.RED);
    }

    /**
     * Constracts jumper of a given color
     * @param color the color for jumper
     */
    public Jumper(Color color) {
        setColor(color);
    }

    /**
     * Jumps if can jump, moves otherwise.
     * Moves if can move, turns otherwise.
     */
    public void act() {
        if (canJump()) {
            jump();
        } else if (canMove()) {
            move();
        } else {
            turn();
        }
    }

    /**
     * Turns the bug 45 degrees to the right without changing its location.
     */
    public void turn() {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next)) {
            moveTo(next);
        } else {
            removeSelfFromGrid();
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }
    
    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     * @return true if this bug can move
     */
    public boolean canMove() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next)) {
            return false;
        }
        Actor neighbor = gr.get(next);
        return (neighbor == null) || (neighbor instanceof Flower);
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
    }
    
    /**
     * Jumps the bug forward two ceils.
     * Destination must be vaild or jumper remove itself from the grid
     */
    public void jump() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location nNext = next.getAdjacentLocation(getDirection());
        if (gr.isValid(nNext)) {
            moveTo(nNext);
        } else {
            removeSelfFromGrid();
        }
    }

    /**
     * Tests whether this bug can jump forward into a location that is empty or
     * contains a flower.
     * @return true if this bug can jump
     */
    public boolean canJump() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next)) {
            return false;
        }
        Location nNext = next.getAdjacentLocation(getDirection());
        if (!gr.isValid(nNext)) {
            return false;
        }
        Actor neighbor = gr.get(nNext);
        return (neighbor == null);
        // ok to move into empty location
        // not ok to move onto any other actor
    }
}
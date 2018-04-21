import info.gridworld.actor.Bug;

/**
 * A <code>SpiralBug</code> traces in spiral pattern of a given initial size. <br />
 */

public class SpiralBug extends Bug {
    private int steps;
    private int sideLength;

    /**
     * Constructs a circle bug that traces in spiral pattern of a given initial side length
     * @param length the side length
     */

    public SpiralBug(int length) {
        steps = 0;
        sideLength = length;
    }

     /**
     * Moves to the next location of the spiral pattern 
     */

    public void act() {
        if (steps < sideLength && canMove()) {
            move();
            steps++;
        } else {
            turn();
            turn();
            steps = 0;
            sideLength++;
        }
    }
}

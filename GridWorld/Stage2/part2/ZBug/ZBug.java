import info.gridworld.actor.Bug;

/**
 * A <code>ZBug</code> traces in 'Z' pattern of a given size. <br />
 */

public class ZBug extends Bug {
    public static final int EAST = 90;
    public static final int SOUTHWEST = 225;

    private int steps;
    private int sideLength;
    private int round;

    /**
     * Constructs a Z bug that traces in 'Z' pattern of a given side length
     * @param length the side length
     */

    public ZBug(int length) {
        steps = 0;
        round = 0;
        sideLength = length;
        setDirection(EAST);
    }

     /**
     * Moves to the next location of the spiral pattern
     */

    public void act() {
        if (steps < sideLength && canMove()) {
            move();
            steps++;
        } else if (round == 0) {
            round++;
            steps = 0;
            setDirection(SOUTHWEST);
        } else if (round == 1) {
            round++;
            steps = 0;
            setDirection(EAST);
        } else {
            round++;
            steps = sideLength;
        }
    }
}

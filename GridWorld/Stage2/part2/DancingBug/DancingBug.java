import info.gridworld.actor.Bug;

/**
 * A <code>DancingBug</code> dances certain times. <br />
 */

public class DancingBug extends Bug {
    private int steps;
    private int[] times;

    /**
     * Constructs a dancing bug that traces like a bug but dances before moving
     * @param turns the dancing times
     */

    public DancingBug(int[] turns) {
        steps = 0;
        times = new int[turns.length];
        System.arraycopy(turns, 0, times, 0, turns.length);
    }

    /**
     * Dances and moves to the next location
     */

    public void act() {
        dance();
        steps++;
        super.act();
    }

    private void dance() {
        if (steps == times.length) {
            steps = 0;
        }

        for (int i = 0; i < times[steps]; ++i) {
            turn();
        }
    }
}

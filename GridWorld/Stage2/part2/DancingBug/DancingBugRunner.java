import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/*
 * This Class runs a world that contains dancing bugs.<br />
 */
public final class DancingBugRunner {
    public static final int ROW = 5;
    public static final int COLUMN = 5;
    public static final int[] TIMES = {2, 9, 3, 7, 1, 6, 0};

    private DancingBugRunner() {}

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        DancingBug alice = new DancingBug(TIMES);
        alice.setColor(Color.ORANGE);
        world.add(new Location(ROW, COLUMN), alice);
        world.show();
    }
}

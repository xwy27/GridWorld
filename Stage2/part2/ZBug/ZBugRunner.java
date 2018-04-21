import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/*
 * This Class runs a world that contains z bugs.<br />
 */
public final class ZBugRunner {
    public static final int LENGTH = 3;
    public static final int ROW = 5;
    public static final int COLUMN = 5;

    private ZBugRunner() {}

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        ZBug alice = new ZBug(LENGTH);
        alice.setColor(Color.ORANGE);
        world.add(new Location(ROW, COLUMN), alice);
        world.show();
    }
}

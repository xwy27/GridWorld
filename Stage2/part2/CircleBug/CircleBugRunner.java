import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/*
 * This Class runs a world that contains circle bugs.<br />
 */
public final class CircleBugRunner {
    public static final int LENGTH = 3;
    public static final int ROW = 5;
    public static final int COLUMN = 5;

    private CircleBugRunner() {}

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        CircleBug alice = new CircleBug(LENGTH);
        alice.setColor(Color.ORANGE);
        world.add(new Location(ROW, COLUMN), alice);
        world.show();
    }
}

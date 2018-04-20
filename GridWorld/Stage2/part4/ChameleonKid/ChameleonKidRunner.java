import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains ChameleonKid critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class ChameleonKidRunner {
    private ChameleonKidRunner() {}

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.add(new Location(Number.SEVEN, Number.EIGHT), new Rock());
        world.add(new Location(Number.THREE, Number.THREE), new Rock());
        world.add(new Location(Number.TWO, Number.EIGHT), new Rock(Color.BLUE));
        world.add(new Location(Number.FIVE, Number.FIVE), new Rock(Color.PINK));
        world.add(new Location(Number.ONE, Number.FIVE), new Rock(Color.RED));
        world.add(new Location(Number.SEVEN, Number.TWO), new Rock(Color.YELLOW));
        world.add(new Location(Number.FOUR, Number.FOUR), new ChameleonKid());
        world.add(new Location(Number.FIVE, Number.EIGHT), new ChameleonKid());
        world.show();
    }
}
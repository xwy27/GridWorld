import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains BlusterCritterRunner critters. <br />
 */
public final class BlusterCritterRunner {
    private BlusterCritterRunner() {}
    
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        for (int i = 0; i <= 2; ++i) {
            for (int j = 1; j <= Number.FOUR; ++j) {
                if (!((i == 1 && j == 2) || (i == 1 && j == Number.THREE))) {
                    world.add(new Location(i, j), new Rock());
                }
            }
        }
        world.add(new Location(Number.ONE, Number.TWO), new BlusterCritter(0));
        world.add(new Location(Number.ONE, Number.THREE), new BlusterCritter(Number.THREE));
        world.show();
    }
}
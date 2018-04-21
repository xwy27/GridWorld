import info.gridworld.actor.*;
import info.gridworld.grid.Location;

/**
 * This class runs a world with additional grid choices.
 */
public final class SparseGridRunner {
    private SparseGridRunner() {}

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.addGridClass("SparseBoundedGrid");
        world.addGridClass("SparseBoundedGrid2");
        world.addGridClass("UnboundedGrid2");
        world.add(new Location(Number.TWO, Number.TWO), new Critter());
        world.show();
    }
}
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>KingCrab</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 */
public class KingCrab extends CrabCritter {
    /**
     * Processes the elements of actors. Implemented to move actors one location 
     * away from the KingCrab, otherwise, "eat" (i.e. remove) the actors
     * @param actors the actors to be processed
     */
    public void processActors(ArrayList<Actor> actors) {
        for (Actor a : actors) {
            if (!moveAway(a)) {
                a.removeSelfFromGrid();
            }
        }
    }

    /**
     * Move actor one location away from king crab critter if possible.
     * @param actor actor to move
     * @return true if actor moves
     */
    public boolean moveAway(Actor actor) {
        ArrayList<Location> empty =
            getGrid().getEmptyAdjacentLocations(actor.getLocation());
        for (Location loc : empty) {
            if (distance(getLocation(), loc) > Number.ONE) {
                actor.moveTo(loc);
                return true;
            }
        }
        return false;
    }

    /**
     * Get distance between two locations.
     * @param xloc the first location
     * @param yloc the second location
     * @return distance between two locations
     */
    public int distance(Location xloc, Location yloc) {
        int xSub = xloc.getRow() - yloc.getRow();
        int ySub = xloc.getCol() - yloc.getCol();
        return (int)Math.sqrt(xSub * xSub - ySub * ySub);
    }
}

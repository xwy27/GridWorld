import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>ChameleonKid</code> takes on the color of neighboring actors in
 * front or behind it as it moves throug the gird. <br />
 */
public class ChameleonKid extends ModifiedChameleonCritter {
    /**
     * Gets the actors for processing. Implemented to return the actors that
     * occupy front or backforward grid locations.
     * @return a list of actors that this critter wishes to process.
     */
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] directions = { Location.AHEAD, Location.HALF_CIRCLE };
        for (Location loc : getLocationsInDirections(directions)) {
            Actor temp = getGrid().get(loc);
            if (temp != null) {
                actors.add(temp);
            }
        }
        return actors;
    }

    /**
     * Finds the valid adjacent locations of this critter in different
     * directions.
     * @param directions - an array of directions (which are relative to the
     * current direction)
     * @return a set of valid locations that are neighbors of the current
     * location in the given directions
     */
    public ArrayList<Location> getLocationsInDirections(int[] directions) {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();

        for (int d : directions) {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc)) {
                locs.add(neighborLoc);
            }
        }
        return locs;
    }
}

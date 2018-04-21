import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>QuickCrab</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 */
public class QuickCrab extends CrabCritter {
    /**
     * @return list of empty locations immediately 
     * to the right and to the left two locations away
     * if the location and intervening location are both
     * empty
     */
    public ArrayList<Location> getMoveLocations() {
        ArrayList<Location> locs = new ArrayList<Location>();
        Location loc = getLocation();
        if (validLocationInDirection(Location.LEFT, loc) &&
            validLocationInDirection(Location.LEFT, loc.getAdjacentLocation(Location.LEFT))) {
                locs.add(loc.getAdjacentLocation(Location.LEFT).getAdjacentLocation(Location.LEFT));
        }

        if (validLocationInDirection(Location.RIGHT, loc) &&
            validLocationInDirection(Location.RIGHT, loc.getAdjacentLocation(Location.RIGHT))) {
                locs.add(loc.getAdjacentLocation(Location.RIGHT).getAdjacentLocation(Location.RIGHT));
        }

        if (locs.size() == 0) {
            return super.getMoveLocations();
        }
        return locs;
    }

    /**
     * Judge the adjacent location of this critter in given
     * direction is valid
     * @param direction - direction (which are relative to the
     * current direction)
     * @return true if the location in given direction is valid
     */
    public boolean validLocationInDirection(int direction, Location loc) {
        Grid gr = getGrid();
        Location neighborLoc = loc.getAdjacentLocation(getDirection() + direction);
        if (gr.isValid(neighborLoc) && gr.get(neighborLoc) == null) {
            return true;
        }
        return false;
    }
}

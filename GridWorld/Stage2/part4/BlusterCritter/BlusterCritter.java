import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;
/**
 * A <code>BlusterCritter</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 */
public class BlusterCritter extends Critter {
    private static final int BRIGHT = 2;
    private static final int DARK = -2;
    private static final int ZERO = 0;
    private static final int CEIL = 255;
    
    private int courageFactor;
    
    /**
     * Construct a BlusterCritter with a c courageFactor
     */
    public BlusterCritter (int c) {
        super();
        courageFactor = c;
    }

    /**
     * Gets the actors for processing. Implemented to return the actors that
     * occupy grid locations within two steps of its current location.
     * @return a list of actors that this critter wishes to process.
     */
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        Location loc = getLocation();
        for (int row = loc.getRow() - 2; row <= loc.getRow() + 2; row++) {
            for (int col = loc.getCol() - 2; col <= loc.getCol() + 2; col++) {
                if (getGrid().isValid(new Location(row, col))) {
                    Actor temp = getGrid().get(new Location(row, col));
                    if (temp != null && temp != this) {
                        actors.add(temp);
                    }
                }
            }
        }
        return actors;
    }

    /**
     * Process the actors. Implement to count the number of critters
     * that occupy grid location within two stpes of this critter's
     * location. If number is fewer than courageFactor, the critter's
     * color brighten, otherwise, darken
     * @param actors the actors to be processed
     */
    public void processActors(ArrayList<Actor> actors) {
        int count = 0;
        for (Actor temp : actors) {
            if (temp instanceof Critter) {
                count++;
            }
        }
        if (count < courageFactor) {
            changeColor(BRIGHT);
        } else {
            changeColor(DARK);
        }
    }

    /**
     * Change the color of the BlusterCritter
     * @param factor the color change factor
     */
    private void changeColor(int factor) {
        Color c = getColor();
        int[] color = { c.getRed(), c.getGreen(), c.getBlue()};
        for (int i = 0; i < Number.THREE; ++i) {
            if (color[i] + factor <= CEIL && color[i] + factor >= 0) {
                color[i] += factor;
            }
        }
        setColor(new Color(color[ZERO], color[Number.ONE], color[Number.TWO]));
    }
}

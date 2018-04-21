import info.gridworld.actor.Rock;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;

import java.util.ArrayList;

/**
 * A <code>RockHound</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 */
public class RockHound extends Critter {
    /**
     * Process the actors which are this critter's neightbours.
     * Implement to 'eat'(remove) all the rocks.
     * @param actors the actors to be processed
     */
    public void processActors(ArrayList<Actor> actors) {
        for (Actor temp : actors) {
            if (temp instanceof Rock) {
                temp.removeSelfFromGrid();
            }
        }
    }
}

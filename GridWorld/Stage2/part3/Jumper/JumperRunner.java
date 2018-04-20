import java.awt.Color;
import info.gridworld.grid.*;
import info.gridworld.actor.*;

/*
 * This Class runs a world that contains jumpers.<br />
 */
public final class JumperRunner {
    public static final int ROW = 5;
    public static final int COLUMN = 5;

    private JumperRunner() {}

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Rock(Color.green));
        world.add(new Flower());
        world.add(new Location(ROW, COLUMN), alice);
        world.show();
    }
}

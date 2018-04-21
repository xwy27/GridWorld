Set 7
1. What methods are implemented in Critter?  
    ```act, getActors, processActors, getMoveLocations, selectMoveLocation, makeMove```
    ```java
    //@file Critter.java
    //@line 38
    public void act() {}
    //@line 56
    public ArrayList<Actor> getActors() {}
    //@line 71
    public void processActors(ArrayList<Actor> actors) {}
    //@line 88
    public ArrayList<Location> getMoveLocations() {}
    //@line 104
    public Location selectMoveLocation(ArrayList<Location> locs) {}
    //@line 125
    public void makeMove(Location loc) {}
    ```
1. What are the five basic actions common to all critters when they act?  
    ```getActors, processActors, getMoveLocations, selectMoveLocation, makeMove```
    ```java
    //@file Critter.java
    //@line 56
    public ArrayList<Actor> getActors() {}
    //@line 71
    public void processActors(ArrayList<Actor> actors) {}
    //@line 88
    public ArrayList<Location> getMoveLocations() {}
    //@line 104
    public Location selectMoveLocation(ArrayList<Location> locs) {}
    //@line 125
    public void makeMove(Location loc) {}
    ```
1. Should subclasses of Critter override the getActors method? Explain.  
    **Yes.** Because different types of critter may controls different set of actors.
    ```java
    //@file Critter.java
    //@line 51
    /** ...
     * Override this method in subclasses to
     * look elsewhere for actors to process.<br />
     * ...
     */
    public ArrayList<Actor> getActors() {}
    ```
1. Describe the way that a critter could process actors.  
    **Varies.** It may ask them to change color, move or eat them.
    ```java
    //@file Critter.java
    //@line 63
    /** ...
     * Implemented to "eat" (i.e. remove) selected actors
     * that are not rocks or critters. Override this method in subclasses to
     * process actors in a different way. <br />
     * ...
     */
    public void processActors(ArrayList<Actor> actors) {}
    ```
1. What three methods must be invoked to make a critter move? Explain each of these methods.  
    ```getMoveLocations, selectMoveLocation, makeMove```

    1. ```getMoveLocations``` gets the locations that the critter can move to.
    1. ```selectMoveLocation``` selects which location to move to from the ```getMoveLocations``` result.
    1. ```makeMove``` makes the critter move to the location selected by ```selectMoveLocation```.
    1. If no location the critter can move to, it remains where it is.
    ```java
    //@file Critter.java
    //@line 44
    ArrayList<Location> moveLocs = getMoveLocations();
    Location loc = selectMoveLocation(moveLocs);
    makeMove(loc);
    //@line 88, 104, 125
    //Refer to the implementions of the methods.
    ```
1. Why is there no Critter constructor?  
    ```Critter``` extends from ```Actor```. If no constructor is given in the class, JAVA will automatically write a default constructor and call the default constructor of the basic class, which refers to ```Actor``` in this example.
    ```java
    //@file Actor.java
    //@line 39
    public Actor() {}
    ```

Set 8
1. Why does act cause a ChameleonCritter to act differently from a Critter even though ChameleonCritter does not override act?  
    The ```act``` method of ```ChameleonCritter``` calls following methods: ```act, getActors, processActors, getMoveLocations, selectMoveLocation, makeMove```.  
    For these methods, ```ChameleonCritter``` only overrides the ```processActors``` and ```makeMove``` methods but they are called by ```act```. So this make it behaves differently.
    ```java
    //@file ChameleonCritter.java
    //@line 36, 50
    public void processActors(ArrayList<Actor> actors) {}
    public void makeMove(Location loc) {}
    ```
1. Why does the makeMove method of ChameleonCritter call super.makeMove?  
    Override code turns the direction of it and the super.makeMove() makes it actually moves to the location.
    ```java
    //@file ChameleonCritter.java
    //@line 50
    public void makeMove(Location loc) {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }
    ```
1. How would you make the ChameleonCritter drop flowers in its old location when it moves?  
    **Judging if it moves and whether to place flower.**  
    Rewrite the code as below:
    ```java
    //@file ChameleonCritter.java
    //@line 47
    /**
     * Turns towards the new location and drops flower
     * as it moves.
     */
    public void makeMove(Location loc)
    {
        Location previous = getLocation();
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
        if (previous != loc) {
            Flower flower = new Flower(getColor());
            flower.putSelfInGrid(getGrid(), previous);
        }
    }
    ```
1. Why doesn’t ChameleonCritter override the getActors method?  
    Because it processes the actors nearby just like the basic ```Critter``` class.
1. Which class contains the getLocation method?  
    The ```Actor``` class.
    ```java
    //@file Actor.java
    //@line 102
    public Location getLocation() {
        return location;
    }
    ```
1. How can a Critter access its own grid?  
    Call the ```getGrid``` method which inherits from the ```Actor``` class.
    ```java
    //@file Actor.java
    //@line 92
    public Grid<Actor> getGrid() {
        return grid;
    }
    ```

Set 9

1. Why doesn’t CrabCritter override the processActors method?  
    Because its behavior of ```processActor``` is still eating the actors which is the same as the ```Critter``` class.
    ```java
    //@file Critter.java
    //@line 73
    for (Actor a : actors) {
        if (!(a instanceof Rock) && !(a instanceof Critter))
            a.removeSelfFromGrid();
    }
    ```
1. Describe the process a CrabCritter uses to find and eat other actors. Does it always eat all neighboring actors? Explain.  
    The ```CrabCritter``` class call ```getActors``` to look for actors locates in front, left or right of it. Then call the ```processActors``` to 'eat' the actors matched in ```getActors```.  
    **No.** Beacuse it only concerns about the location in front, left and right of it.
    ```java
    //@file CrabCritter.java
    //@line 46
    ArrayList<Actor> actors = new ArrayList<Actor>();
    int[] dirs =
        { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
    for (Location loc : getLocationsInDirections(dirs) {
        Actor a = getGrid().get(loc);
        if (a != null)
            actors.add(a);
    }
    ```
1. Why is the getLocationsInDirections method used in CrabCritter?  
    This method returns the locations matched in the array parameter. The method is used to find the front, left and right location of the ```CrabCritter```, which could determine the to be eaten actors' location.
    ```java
    //@file CrabCritter.java
    //@line 49
    for (Location loc : getLocationsInDirections(dirs) {
        Actor a = getGrid().get(loc);
        if (a != null)
            actors.add(a);
    }
    ```
1. If a CrabCritter has location (3, 4) and faces south, what are the possible locations for actors that are returned by a call to the getActors method?  
    **(4, 4), (4, 5) and (4, 3)**
1. What are the similarities and differences between the movements of a CrabCritter and a Critter?  
    **Similarities:**  
	No turning operation and randomly select the location to move into.  
    **Differences:**
		- Critter:
			1. Any direction is accetable. 
			2. Remain location while cannot move.
		- CrabCritter:   
			1. Only moves towards left or right direction.
			2. Randomly turn left or right while cannot move.
    ```java
    //@file CrabCritter.java
    //@line 79
    if (loc.equals(getLocation())) {
        double r = Math.random();
        int angle;
        if (r < 0.5)
            angle = Location.LEFT;
        else
            angle = Location.RIGHT;
        setDirection(getDirection() + angle);
    } else
        super.makeMove(loc);
    ```
1. How does a CrabCritter determine when it turns instead of moving?  
    When the parameter ```loc``` for ```makeMove``` equals the location of the ```CrabCritter```, it turns.
    ```java
    //@file CrabCritter.java
    //@line 79
    if (loc.equals(getLocation())) {
        double r = Math.random();
        int angle;
        if (r < 0.5)
            angle = Location.LEFT;
        else
            angle = Location.RIGHT;
        setDirection(getDirection() + angle);
    }
    ```
1. Why don’t the CrabCritter objects eat each other?  
    Beacuse it extends the ```Critter``` which deines it doesn't eat other ```Critter```.
    ```java
    //@file Critter.java
    //@line 73
    for (Actor a : actors) {
        if (!(a instanceof Rock) && !(a instanceof Critter))
            a.removeSelfFromGrid();
    }
    ```
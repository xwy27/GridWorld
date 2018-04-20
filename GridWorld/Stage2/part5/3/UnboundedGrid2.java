import info.gridworld.grid.*;

import java.util.ArrayList;

/**
 * An <code>UnboundedGrid</code> is a rectangular grid with an unbounded
 * number of rows and columns. <br />
 */
public class UnboundedGrid2<E> extends AbstractGrid<E> {
    private Object[][] occupantArray;
    private int dimension;

    /**
     * Constructs an empty unbounded grid.
     */
    public UnboundedGrid2() {
        dimension = 16;
        occupantArray = new Object[dimension][dimension];
    }

    public int getNumRows() {
        return dimension;
    }

    public int getNumCols() {
        return dimension;
    }

    public boolean isValid(Location loc) {
        return loc.getCol() >= 0 && loc.getRow() >= 0;
    }

    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < dimension; r++) {
            for (int c = 0; c < dimension; c++) {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null) {
                    theLocations.add(loc);
                }
            }
        }
        return theLocations;
    }

    public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (loc.getCol() >= dimension || loc.getRow() >= dimension) {
            return null;
        }
        return (E) occupantArray[loc.getRow()][loc.getCol()];
    }
    
    public E put(Location loc, E obj) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
            
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }
        
        if (loc.getCol() >= dimension || loc.getRow() >= dimension) {
            resize(loc);
        }
        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (loc.getCol() >= dimension || loc.getRow() >= dimension) {
            return null;
        }
        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }

    /**
     * Resize the grid by double the dimension to meet the request.
     * @param loc the loction to put an occupant
     */
    public void resize(Location loc) {
        // Double size until meet the request
        int size = dimension;
        while (size <= loc.getCol() || size <= loc.getRow()) {
            size *= Number.TWO;
        }
        // Put stored objects in the new array
        Object[][] temp = new Object[size][size];
        for (int r = 0; r < dimension; r++) {
            for (int c = 0; c < dimension; c++) {
                temp[r][c] = occupantArray[r][c];
            }
        }
        // Finish resize task
        occupantArray = temp;
        dimension = size;
    }
}

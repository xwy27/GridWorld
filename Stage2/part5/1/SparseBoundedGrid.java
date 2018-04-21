import info.gridworld.grid.*;

import java.util.ArrayList;

/**
 * <code>SparseBoundedGrid</code> is a rectangular grid with 
 * bounded edges with sparse objects in the gird.<br />
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E> {
    //Array for storing the occupants
    private SparseBoundedNode[] occupantArray;
    private int col;
    private int row;

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        col = cols;
        row = rows;
        occupantArray = new SparseBoundedNode[rows];
    }

    public int getNumRows() {
        return row;
    }

    public int getNumCols() {
        return col;
    }

    public boolean isValid(Location loc) {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows() &&
               0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++) {
            SparseBoundedNode temp = occupantArray[r];
            while (temp != null) {
                Location loc = new Location(r, temp.getCol());
                theLocations.add(loc);
                temp = temp.getNext();
            }
        }
        return theLocations;
    }

    public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        SparseBoundedNode temp = occupantArray[loc.getRow()];
        int targetCol = loc.getCol();
        while (temp != null && targetCol != temp.getCol()) {
            temp = temp.getNext();
        }
        if (temp != null) {
            return (E)temp.getOccupant();
        }
        return null;
    }

    public E put(Location loc, E obj) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }

        // Remove the old occupant
        E oldOccupant = remove(loc);
        // Add the node storing new object to its row in the front.
        // We don't concern about the order of the nodes in a row
        // Because we always search by loop.
        SparseBoundedNode temp = occupantArray[loc.getRow()];
        occupantArray[loc.getRow()] = new SparseBoundedNode(obj, loc.getCol(), temp);
        return oldOccupant;
    }

    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }

        // Remove the object from the grid.
        E r = get(loc);
        if (r == null) {
            return null;
        }

        SparseBoundedNode preNode = occupantArray[loc.getRow()];
        SparseBoundedNode curNode = occupantArray[loc.getRow()];
        int targetCol = loc.getCol();
        while (curNode != null && curNode.getCol() != targetCol) {
            preNode = curNode;
            curNode = curNode.getNext();
        }
        // At the first position of the row.
        if (curNode == occupantArray[loc.getRow()]) {
            occupantArray[loc.getRow()] = curNode.getNext();
        } else {
            if (curNode != null) {
                preNode.setNext(curNode.getNext());
            }
        }
        return r;
    }
}
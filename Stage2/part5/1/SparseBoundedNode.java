/**
 * <code>SparseBoundedNode</code> provides the node declaration and 
 * defination for SparseBoundedGrid containing a grid occupant and 
 * a column index. <br />
 */
public class SparseBoundedNode {
    private Object occupant;
    private int col;
    private SparseBoundedNode next;

    /**
     * Construct a sparseBoundedNode with value occupant, col and next.
     * @param occupant the initial occupant object
     * @param col the initial column index
     * @param next the initial next node
     */
    public SparseBoundedNode(Object occupant, int col, SparseBoundedNode next) {
        this.occupant = occupant;
        this.col = col;
        this.next = next;
    }

    /**
     * Get the current occupant in the node.
     * @return occupant
     */
    public Object getOccupant() {
        return occupant;
    }
    
    /**
     * Get the current column index in the node.
     * @return col
     */
    public int getCol() {
        return col;
    }
    
    /**
     * Get the current next node.
     * @return next
     */
    public SparseBoundedNode getNext() {
        return next;
    }
    
    /**
     * Set a new occupant in the node.
     * @param occupant new occupant object
     */
    public void setOccupant(Object occupant) {
        this.occupant = occupant;
    }
    
    /**
     * Set a new next node.
     * @param next new next node
     */
    public void setNext(SparseBoundedNode next) {
        this.next = next;
    }
}
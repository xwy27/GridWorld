Set 10

1. Where is the isValid method specified? Which classes provide an implementation of this method?  
    1. ```Grid```class.
        ```java
        //@file Grid.java
        //@line 50
        boolean isValid(Location loc);
        ```
    1. ```BoundedGrid``` and ```UnboundedGrid``` class.
        ```java
        //@file BoundedGrid.java
        //@line 60
        //@file UnboundedGrid.java
        //@line 53
        boolean isValid(Location loc);
        ```
1. Which AbstractGrid methods call the isValid method? Why don’t the other methods need to call it?  
    ```getValidAdjacentLocations``` method.  
    Because other methods in ```AbstractGrid``` class all call this method and this method will call ```isValid``` method. So it is no necessary to call it again.
    ```java
    //@file AbstractGrid.java
    //@line 44
    if (isValid(neighborLoc))
        locs.add(neighborLoc);
    ```
1. Which methods of the Grid interface are called in the getNeighbors method? Which classes provide implementations of these methods?  
    ```get``` and ```getOccupiedAdjacentLocations``` are called.
    ```java
    //@file AbstractGrid.java
    //@line 31
    for (Location neighborLoc : getOccupiedAdjacentLocations(loc))
        neighbors.add(get(neighborLoc));
    ```
    ```get``` method is implemented in both ```BoundedGrid``` and ```UnboundedGrid``` class.  
    ```getOccupiedAdjacentLocations``` is implemented in ```AbstractGrid``` class.
    ```java
    //@file BoundedGrid.java
    //@line 85
    //@file UnboundedGrid.java
    //@line 66
    public E put(Location loc, E obj)
    //@file AbstractGrid.java
    //@line 62
    public ArrayList<Location> getOccupiedAdjacentLocations(Location loc)
    ```
1. Why must the get method, which returns an object of type E, be used in the getEmptyAdjacentLocations method when this method returns locations, not objects of type E?  
    ```getEmptyAdjacentLocations``` calls the ```get``` method to test if the adjacent location is empty because ```get``` method can return a *null* or an *object* occupied the location checking.
    ```java
    //@file AbstractGrid.java
    //@line 44
    if (get(neighborLoc) == null)
        locs.add(neighborLoc);
    ```
1. What would be the effect of replacing the constant Location.HALF_RIGHT with Location.RIGHT in the two places where it occurs in the getValidAdjacentLocations method?  
    If replaced, the ```getValidAdjacentLocations``` will only check four loctions which are back, front, left and right. In this case, it will miss some locations hoped to check.
    ```java
    //@file AbstractGrid.java
    //@line 41
    for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
    ```
Set 11

1. What ensures that a grid has at least one valid location?  
    The constructor of the ```boundedgrid``` will check the valid parameter rows and cols. If not, IllegalArgumentException will be thrown.
    ```java
    //@file BoundedGrid.java
    //@line 41
    if (rows <= 0)
        throw new IllegalArgumentException("rows <= 0");
    if (cols <= 0)
        throw new IllegalArgumentException("cols <= 0");
    ```
1. How is the number of columns in the grid determined by the getNumCols method? What assumption about the grid makes this possible?  
    By reading the length of the array ```occupantArray[0]```.  
    The **constructor** ensures number of rows is *non-null*.
    ```java
    //@file BoundedGrid.java
    //@line 55
    // Note: according to the constructor precondition, numRows() > 0, so
    // theGrid[0] is non-null.
    return occupantArray[0].length;
    ```
1. What are the requirements for a Location to be valid in a BoundedGrid?  
    The row value of the location must lie in the interval $[0, number of rows in the BoundedGrid]$ and  the column value of the location must lie in the interval $[0, number of columns in the BoundedGrid]$.
    ```java
    //@file BoundedGrid.java
    //@line 62
    return 0 <= loc.getRow() && loc.getRow() < getNumRows()
        && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    ```
    
In the next four questions, let r = number of rows, c = number of columns, and n = number of occupied locations.

1. What type is returned by the getOccupiedLocations method? What is the time complexity (Big-Oh) for this method?  
    ```ArrayList<Location>``` is returned.  
    $O(r * c)$ is the total time complexity for the method for every loction in the grid must be accessed.
    ```java
    //@file BoundedGrid.java
    //@line 71
    for (int r = 0; r < getNumRows(); r++) {
        for (int c = 0; c < getNumCols(); c++) {
            // If there's an object at this location, put it in the array.
            Location loc = new Location(r, c);
            if (get(loc) != null)
                theLocations.add(loc);
        }
    }
    ```
1. What type is returned by the get method? What parameter is needed? What is the time complexity (Big-Oh) for this method?
    ```E``` is returned and a ```Location``` object is required as a parameter. The time complexity is $O(1)$ since it just access the value stored in an array.
    ```java
    //@file BoundGrid.java
    //@line 85
    public E get(Location loc) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
            + " is not valid");
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    }
    ```
1. What conditions may cause an exception to be thrown by the put method? What is the time complexity (Big-Oh) for this method?  
    An **invalid loction** or the object to be added is **null** causes an exception.  
    The time complexity is $O(1)$ since it just access the value stored in an array.
    ```java
    //@file BoundGrid.java
    //@line 95
    if (!isValid(loc))
        throw new IllegalArgumentException("Location " + loc
                + " is not valid");
    if (obj == null)
        throw new NullPointerException("obj == null");
    ```
1. What type is returned by the remove method? What happens when an attempt is made to remove an item from an empty location? What is the time complexity (Big-Oh) for this method?  
    ```E``` type is returned.  
    The empty location stores null object and null is removed. Then, **null is returned**.  
    The time complexity is $O(1)$ since it just access the value stored in an array.
    ```java
    //@file BoundGrid.java
    //@line 114
    E r = get(loc);
    occupantArray[loc.getRow()][loc.getCol()] = null;
    return r;
    ```
1. Based on the answers to questions 4, 5, 6, and 7, would you consider this an efficient implementation? Justify your answer.  
    **Yes.** For above invoked methods, only the ```getOccupiedLocations``` method is $O(r * c)$ while others are $O(1)$. These **low method costs** make it an efficient implementation.

Set 12

1. Which method must the Location class implement so that an instance of HashMap can be used for the map? What would be required of the Location class if a TreeMap were used instead? Does Location satisfy these requirements?  
    ```equlas``` and ```hasCode``` methods.  
    TreeMap requires the keys to be comparable. So if TreeMap is used, we should define the **compare operations**.  
    **Yes.**
    ```java
    //@file Location.java
    //@line 219, 232
    public boolean equals(Object other)
    public int hashCode()
    ```
1. Why are the checks for null included in the get, put, and remove methods? Why are no such checks included in the corresponding methods for the BoundedGrid?  
    In BoundedGrid, the ```isValid``` method helps check the loction to be non-null and within the bound. But for UnboundedGrid class, it uses HashMap, which assumes **every location is valid but null is not acceptable**. However, unlike BoundedGrid, the ```isValid``` method **returns true forever** in UnboundedGrid. So it is necessary to check location to be non-null in thest methods.
    ```java
    //@file UnboudedGrid.java
    //@line 53
    public boolean isValid(Location loc) {
        return true;
    }
    ```
1. What is the average time complexity (Big-Oh) for the three methods: get, put, and remove? What would it be if a TreeMap were used instead of a HashMap?  
    $O(1)$ for methods by HashMap because only math calculation is included.  
    $O(log n)$ for methods by TreeMap becasue accessing the value must travel the balanced tree. 
1. How would the behavior of this class differ, aside from time complexity, if a TreeMap were used instead of a HashMap?  
    A TreeMap is used instead will make the compare operations convinent and efficient while the get, put and remove becomes higher cost for every time it must travel the tree.
1. Could a map implementation be used for a bounded grid? What advantage, if any, would the two-dimensional array implementation that is used by the BoundedGrid class have over a map implementation?  
    **Yes**.If HashMap is used instead, the time complexity of ```getOccupiedLocations``` method will decrease to $O(n)$, in which, n is the number of objects occupied locations in the grid.  
    For a two-dimensional array implementation, when the grid is nearly full, it saves memory for it only stores the item while the HashMap store both the item and its location. 

[Coding Exercises] Consider using a HashMap or TreeMap to implement the SparseBoundedGrid. How could you use the UnboundedGrid class to accomplish this task? Which methods of UnboundedGrid could be used without change?

Fill in the following chart to compare the expected Big-Oh efficiencies for each implementation of the SparseBoundedGrid.

Let r = number of rows, c = number of columns, and n = number of occupied locations  

|Methods|SparseGridNode version|LinkedList<OccupantInCol> version|HashMap version|TreeMap version|
|-------|----------------------|---------------------------------|---------------|---------------|
|getNeighbors                   |$O(c)$ |$O(c)$ |$O(1)$ |$O(logn)$ |
|getEmptyAdjacentLocations      |$O(c)$ |$O(c)$ |$O(1)$ |$O(logn)$ |
|getOccupiedAdjacentLocations   |$O(c)$ |$O(c)$ |$O(1)$ |$O(logn)$ |
|getOccupiedLocations           |$O(r+n)$ |$O(r+n)$ |$O(n)$ |$O(n)$ |
|get                            |$O(c)$ |$O(c)$ |$O(1)$ |$O(logn)$ |
|put                            |$O(c)$ |$O(c)$ |$O(1)$ |$O(logn)$ |
|remove                         |$O(c)$ |$O(c)$ |$O(1)$ |$O(logn)$ |		
				
[Code Exercises]Consider an implementation of an unbounded grid in which all valid locations have non-negative row and column values. The constructor allocates a 16 x 16 array. When a call is made to the put method with a row or column index that is outside the current array bounds, double both array bounds until they are large enough, construct a new square array with those bounds, and place the existing occupants into the new array.

Implement the methods specified by the Grid interface using this data structure. What is the Big-Oh efficiency of the get method? What is the efficiency of the put method when the row and column index values are within the current array bounds? What is the efficiency when the array needs to be resized?

+ $O(1)$ *Still access value from the array*
+ $O(1)$ *Within current bounds just like BoundedGrid.*
+ $O(row \times column)$ *Row and Column is the current array's number of row and column. Need to scan the current array and copy them into the new array.*
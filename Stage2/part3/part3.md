Question 1
[set3] 1. How would you access the row value for loc1?


By accessing the method ```getRow()```.
```java
  //@file Location.java
  //@line 110
  public int getRow();
```
6140:0
Question 2
[set3] 2. What is the value of b after the following statement is executed?


**False**
110:0
Question 3
[set3] 3. What is the value of loc3 after the following statement is executed?


**For loc3, Row = 4, Column = 4.**
160:0
Question 4
[set3] 4. What is the value of dir after the following statement is executed?


**135 (degrees)**
120:0
Question 5
[set3] 5. How does the getAdjacentLocation method know which adjacent location to return?


The method has an parameter called direction and return the adjacent location by following steps:
1. Mod direction by 360 (Ensure it is in a full circle degree)
2. Check the closest 'direction' (a product of 45) to the direction from east to north and return it.
```java
  //@file Location.java
  //@line 130
  public Location getAdjacentLocation(int direction) {}
```
8580:0
Question 6
[set4] 1. How can you obtain a count of the objects in a grid? How can you obtain a count of the empty locations in a bounded grid?


As below, **```objectNum```** counts the objects in the gird and **```emptyNum```** counts the empty locations in the grid.
```java
int objectNum = grid.getOccupiedLocations().size();
int emptyNum = grid.getNumRows() * getNumCols() - objectNum;
```
*source code:*
```java
  //@file BoundedGrid.java
  //@line 66
  public ArrayList<Location> getOccupiedLocations() {}
```
11420:0
Question 7
[set4] 2. How can you check if location (10,10) is in a grid?


If the flag is *true*, the location(10, 10) is in the grid, otherwise, it is not. 
```java
bool flag = grid.isValid(new Location(10, 10));
```
*source code:*
```java
  //@file Grid.java
  //@line 50
  boolean isValid(Location loc);
```
10380:0
Question 8
[set4] 3. Grid contains method declarations, but no code is supplied in the methods. Why? Where can you find the implementations of these methods?


It is an interface, as well as an abstract class, which declares the methods.   
The classes implement it must define the methods.   
In this way, the classes needn't repeatedly declare the same method and just implements from the interface.  
In this example, we could find the method implementations in ***AbstractGrid, BoundedGrid*** and ***UnboundedGrid*** classes.
4550:0
Question 9
[set4] 4. All methods that return multiple objects return them in an ArrayList. Do you think it would be a better design to return the objects in an array? Explain your answer.


No. **Array needs a fixed size while ArrayList doesn't.**  
If we return objects in array, the gridworld needs to scan all the locations to ensure the number of objects to return and size the array. It is not necessary because we are only concerned about the objects. So, we just fill the ArrayList with the objects. It is enough.
*source code:*
```java
  //@file Grid.java
  //@line 85-129
  ArrayList<DataType> functionName(@parameters);
```
​
9730:0
Question 10
[set5] 1. Name three properties of every actor.


```Color```, ```Location``` and ```Direction```
```java
  //@file Actor.java
  //@line 32
  private Location location;
  private int direction;
  private Color color;
```
8190:0
Question 11
[set5] 2. When an actor is constructed, what is its direction and color?


Original direction is **North** and color is **blue**.
```java
  //@file Actor.java
  //@line 32
  public Actor() {
      color = Color.BLUE;
      direction = Location.NORTH;
  }
```
9220:0
Question 12
[set5] 3. Why do you think that the Actor class was created as a class instead of an interface?


An interface only declare abstract methods without any data while a class has data and method.   
For the ```actor```, **it has methods and also data** to specific it state and get the state. So it is created as a class.
```java
  //@file Actor.java
  //@line 32
  private Location location;
  private int direction;
  private Color color;
```
9550:0
Question 13
[set5] 4. Can an actor put itself into a grid twice without first removing itself? Can an actor remove itself from a grid twice? Can an actor be placed into a grid, remove itself, and then put itself back? Try it out. What happens?


Try samples, we get that:
+ An actor **cannot** put itself into a grid twice without first removing itself. An ```IllegalStateException``` error is throw.
  ```java
    //@file Actor.java
    //@line 117
    if (grid != null)
      throw new IllegalStateException(
        "This actor is already contained in a grid.");
  ```
+ An actor **cannot** remove itself from a grid twice. An ```IllegalStateException``` error is throw.
  ```java
    //@file Actor.java
    //@line 138
    if (grid.get(location) != this)
      throw new IllegalStateException(
        "The grid contains a different actor at location "
          + location + ".");
241050:0
Question 14
[set5] 5. How can an actor turn 90 degrees to the right?


```java
  Bug bug = new Bug();
  bug.seDirection(bug.getDirection() + 90);
```
*source code:*
```java
  //@file Actor.java
  //@line 80
  public void setDirection(int newDirection) {}
```
10230:0
Question 15
[set5] 5. How can an actor turn 90 degrees to the right?(与上题一模一样，请答多一次)


```java
  Bug bug = new Bug();
  bug.seDirection(bug.getDirection() + 90);
```
*source code:*
```java
  //@file Actor.java
  //@line 80
  public void setDirection(int newDirection) {}
```
10230:0
Question 16
[set6] 1. Which statement(s) in the canMove method ensures that a bug does not try to move out of its grid?


By judging whether the location to move in is valid via the following code:  
```java
/*@file Bug.java*/
/*@line 76*/
Location loc = getLocation();
Location next = loc.getAdjacentLocation(getDirection());
if (!gr.isValid(next))  return false;
```
8340:0
Question 17
[set6] 2. Which statement(s) in the canMove method determines that a bug will not walk into a rock?


The statements below ensure the bug will not walk into a rock:
```java
//@file Bug.java
//@line 100
Actor neighbor = gr.get(next);
  return (neighbor == null) || (neighbor instanceof Flower);
```
7290:0
Question 18
[set6] 3. Which methods of the Grid interface are invoked by the canMove method and why?


isValid() and get() method are invoked to ensure the bug will not walk out of the grid or walk into the rock.
```java
/*@file Bug.java*/
//@line 98
if (!gr.isValid(next))
  return false;
Actor neighbor = gr.get(next);
```
8390:0
Question 19
[set6] 4. Which method of the Location class is invoked by the canMove method and why?


getAdjacentLocation() is invoked to get the next location for the bug to walk into.
```java
/*@file Bug.java*/
/*@line 97*/
Location next = loc.getAdjacentLocation(getDirection());
```
6250:0
Question 20
[set6] 5. Which methods inherited from the Actor class are invoked in the canMove method?


```getLocaiont()```, ```getDirection()``` and ```getGrid()```
```java
//@file Bug.java
//@line 93
Grid<Actor> gr = getGrid();
if (gr == null)
  return false;
Location loc = getLocation();
Location next = loc.getAdjacentLocation(getDirection());
```
10270:0
Question 21
[set6] 6. What happens in the move method when the location immediately in front of the bug is out of the grid?


It will remove itself from the grid as if it moves out of the grid.
```java
/*@file Bug.java
  @line 78*/
if (gr.isValid(next))
  moveTo(next);
else
  removeSelfFromGrid();
```
9290:0
Question 22
[set6] 7. Is the variable loc needed in the move method, or could it be avoided by calling getLocation() multiple times?


**Yes.** It is needed to store the location before the bug moves and insert a flower. 
**No.** After the bug moves, the ```getLocation()``` will return the current location of the bug and we could not insert a flower into the former location.
```java
//@file Bug.java
//@line 82
Flower flower = new Flower(getColor());
flower.putSelfInGrid(gr, loc);
```
8570:0
Question 23
[set6] 8. Why do you think the flowers that are dropped by a bug have the same color as the bug?


It is easy to track the bug's path and observe its behavior.
1130:0
Question 24
[set6] 9. When a bug removes itself from the grid, will it place a flower into its previous location?


It depends. 
+ If the method ```removeSelfFromGrid()``` is called directly, there will be no flower left because it is defined in the Actor class which does not leave flower in its track.
  ```java
  //@file Actor.java
  //@line 133
  public void removeSelfFromGrid() {};
  ```
+ If the method called in the method ```move()```, it will leave a flower because there is the code in the method ```move()``` to behave like that.
  ```java
  //@file Bug.java
  //@line 71
  public void move() {};
  ```
13750:0
Question 25
[set6] 10. Which statement(s) in the move method places the flower into the grid at the bug's previous location?


```java
//@file Bug.java
//@line 82
Flower flower = nwe Flower(getColor());
flower.putSelfInGrid(gr, loc);
```
6150:0
Question 26
[set6] 11. If a bug needs to turn 180 degrees, how many times should it call the turn method?


$180 \div 45 = 4$  
**Four times.**
261:15
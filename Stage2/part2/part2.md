Question 1
What is the role of the instance variable sideLength?

Determine the length of the square for the BoxBug to trace.  
*Source code:*
```
  // @file BoxBug.java
  // @line 28
  private int sideLength;
```
​
8210:0
Question 2
What is the role of the instance variable steps?

Record the steps the BoxBug has traced on one side of the square.  
*Source code:*
```
  // @file BoxBug.java
  // @line 27
  private int steps;
```
7230:0
Question 3
Why is the turn method called twice when steps becomes equal to sideLength?

To turn 90 degrees to the right in order to trace the next side of the square.  
*Source code:*
```
  // @file BoxBug.java
  // @line 52
  turn();
  turn();
  steps = 0;
```
9280:0
Question 4
Why can the move method be called in the BoxBug class when there is no move method in the BoxBug code?

Bug class provide a public method ```move()``` and the BoxBug class is **extended** from Bug class. So it **inherits** the ```move()``` method.  
*Source code:*
```
  // @file Box.java
  // @line 71
  public void move()
  
  // @file BoxBug.java
  // @line 25
  public class BoxBug extends Bug
```
11420:0
Question 5
After a BoxBug is constructed, will the size of its square pattern always be the same? Why or why not?

**Yes.** When it is constructed, the sideLength is assigned value and will never be changed.  
*source code:*
```java
  // @file BoxBug.java
  // @line 37
  sideLength = length;
```
7250:0
Question 6
Can the path a BoxBug travels ever change? Why or why not?

**Yes.** If another *Bug or a Rock* is in front of the BoxBug, it will try to move away from them and start to trace a new square.  
*source code:*
```java
  // @file BoxBug.java
  // @line 47
  // Act like an ordinary bug
  move();
```
8420:0
Question 7
When will the value of steps be zero?

**Three situations.**
1. It is constructed
  ```java
  // @file BoxBug.java
  // @line 34
  public BoxBug(int length) {
    steps = 0;
  }
  ```
2. It step equals the sideLength which means it moves to the end of one side of the square
  ```java
  // @file BoxBug.java
  // @line 34
  else {
      turn();
      turn();
      steps = 0;
  }
28650:0

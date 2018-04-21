# Part2-Q&A
1. What is the role of the instance variable sideLength?  
  Determine the length of the square for the BoxBug to trace.  
  *Source code:*
    ```java
      // @file BoxBug.java
      // @line 28
      private int sideLength;
    ```
​
1. What is the role of the instance variable steps?  
  Record the steps the BoxBug has traced on one side of the square.  
  *Source code:*
    ```java
      // @file BoxBug.java
      // @line 27
      private int steps;
    ```

1. Why is the turn method called twice when steps becomes equal to sideLength?  
  To turn 90 degrees to the right in order to trace the next side of the square.  
  *Source code:*
    ```java
      // @file BoxBug.java
      // @line 52
      turn();
      turn();
      steps = 0;
    ```

1. Why can the move method be called in the BoxBug class when there is no move method in the BoxBug code?  
  Bug class provide a public method ```move()``` and the BoxBug class is **extended** from Bug class. So it **inherits** the ```move()``` method.  
  *Source code:*
    ```java
      // @file Box.java
      // @line 71
      public void move()
      
      // @file BoxBug.java
      // @line 25
      public class BoxBug extends Bug
    ```

1. After a BoxBug is constructed, will the size of its square pattern always be the same? Why or why not?  
  **Yes.** When it is constructed, the sideLength is assigned value and will never be changed.  
  *source code:*
    ```java
      // @file BoxBug.java
      // @line 37
      sideLength = length;
    ```

1. Can the path a BoxBug travels ever change? Why or why not?  
  **Yes.** If another *Bug or a Rock* is in front of the BoxBug, it will try to move away from them and start to trace a new square.  
  *source code:*
    ```java
      // @file BoxBug.java
      // @line 47
      // Act like an ordinary bug
      move();
    ```

1. When will the value of steps be zero?  
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
        ```
    3. It cannot move.
        ```java
          // @file BoxBug.java
          // @line 45
          if (steps < sideLength && canMove()) {}
          else {
              steps = 0;
          }
          ```

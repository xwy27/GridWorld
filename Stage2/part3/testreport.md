# Test Reprot For Jumper

<!-- TOC -->

- [Test Reprot For Jumper](#test-reprot-for-jumper)
    - [Description](#description)
    - [Test Report](#test-report)
        - [Code with Analysis](#code-with-analysis)
            - [Case 1](#case-1)
            - [Case 2](#case-2)
            - [Case 3](#case-3)
            - [Case 4](#case-4)
            - [Case 5](#case-5)
        - [Result](#result)

<!-- /TOC -->

## Description

Accroding to the Design Report, we clarify five specific situations for the jumper in the grid world.

1. If the location in front of it is empty, but the location two cells in front contains a flower or a rock
    
1. If the location two cells in front of the jumper is out of the grid
   
1. If it is facing an edge of the grid
    
1. If another actor (not a flower or a rock) is in the cell that is two cells in front of the jumper
    
1. If it encounters another jumper in its path
    
Following are the test cases, code, analysis and the result.

## Test Report

### Code with Analysis

#### Case 1

+ code
    ```java
    /**
     * Case one:
     *   If the front location is empty, but two cells away location contains 
     *   a flower or a rock
     * 
     * Wished Result:
     *   1. Called by act() or move(), Jumper moves one step if the location 
     *      in front of it is empty, but the location two cells in front contains 
     *      a flower or a rock
     *   2. Called by jump(), remove the flower or the rock and Jumper jumps there.
     */
    @Test
    public void testCaseOne() {
        // Rest the world
        reset();
        // Test Result one
        world.add(new Location(Number.TWO, Number.TWO), rock);
        world.add(new Location(Number.FOUR, Number.TWO), jumper);
        jumper.act();
        assertEquals(rock, world.getGrid().get(new Location(Number.TWO, Number.TWO)));
        assertEquals(jumper, world.getGrid().get(new Location(Number.THREE, Number.TWO)));
        // Test Result two
        world.add(new Location(Number.TWO, Number.FOUR), flower);
        world.add(new Location(Number.FOUR, Number.FOUR), jumper2);
        jumper2.jump();
        assertEquals(jumper2, world.getGrid().get(new Location(Number.TWO, Number.FOUR)));
    }
    ```
+ Analysis  
        First, put rock at (2, 2) and jumper at (4, 2). After jumper acts, test whether it is at position (3, 2) and rock remains its position, which is the expected behavior.  
        Second, put flower at (2, 4) and jumper at (4, 4). After jumper jumps, test whether it is at position (2, 4) and flower removed, which is the expected behavior.

#### Case 2

+ code
    ```java
    /**
     * Case two:
     *   If the location two cells in front of the jumper is out of the grid
     * 
     * Wished Result:
     *   1. If called by jump() method, remove the jumper.
     *   1. If called by act() and move() methods, moves one step and leave 
     *      a flower in the previous loaction.
     */
    @Test
    public void testCaseTwo() {
        // Rest the world
        reset();
        // Test Result one
        world.add(new Location(Number.ONE, Number.TWO), jumper);
        jumper.jump();
        assertNull(world.getGrid().get(new Location(Number.ONE, Number.TWO)));
        // Test Result two
        world.add(new Location(Number.ONE, Number.FOUR), jumper2);
        jumper2.act();
        assertEquals(jumper2, world.getGrid().get(new Location(0, Number.FOUR)));
    }
    ```
+ Analysis  
    First, put jumper at (1, 2). After jumper jumps, test whether it is removed, which is the expected behavior.  
    Second, put jumper at (1, 4). After jumper acts, test whether it is at position (0, 4), which is the expected behavior.

#### Case 3

+ code
    ```java
    /**
     * Case three:
     *   If jumper is facing an edge of the grid
     * 
     * Wished Result:
     *   1. If called by move() or jump() methods, remove the jumper.
     *   1. If called by act() method, turn right by 45 degrees.
     */
    @Test
    public void testCaseThree() {
        // Rest the world
        reset();
        // Test Result one
        world.add(new Location(0, Number.TWO), jumper);
        jumper.jump();
        assertNull(world.getGrid().get(new Location(0, Number.TWO)));
        // Test Result two
        world.add(new Location(0, Number.FOUR), jumper2);
        jumper2.act();
        assertEquals(jumper2, world.getGrid().get(new Location(0, Number.FOUR)));
        assertEquals(45 ,jumper2.getDirection());
    }
    ```
+ Analysis  
    First, put jumper at (0, 2). After jumper jumps, test whether it is removed, which is the expected behavior.  
    Second, put jumper at (0, 4). After jumper acts, test whether it is at position (0, 4) and turn 45 degrees to the right, which is the expected behavior.

#### Case 4

+ code
    ```java
    /**
     * Case four:
     *   If another actor (not a flower or a rock) is in the cell that is 
     *   two cells in front of the jumper.
     * 
     * Wished Result:
     *   1. If called by jump() methods, remove the actor and place the jumper there.
     *   1. If called by act() or move() methods, moves one step.
     */
    @Test
    public void testCaseFour() {
        // Rest the world
        reset();
        // Test Result one
        world.add(new Location(Number.TWO, Number.TWO), actor);
        world.add(new Location(Number.FOUR, Number.TWO), jumper);
        jumper.act();
        assertEquals(actor, world.getGrid().get(new Location(Number.TWO, Number.TWO)));
        assertEquals(jumper, world.getGrid().get(new Location(Number.THREE, Number.TWO)));
        // Test Result two
        world.add(new Location(Number.TWO, Number.FOUR), actor2);
        world.add(new Location(Number.FOUR, Number.FOUR), jumper2);
        jumper2.jump();
        assertEquals(jumper2, world.getGrid().get(new Location(Number.TWO, Number.FOUR)));
    }
    ```
+ Analysis  
    First, put actor at (2, 2) and jumper at (4, 2). After jumper acts, test whether actor remains and jumper is at (3, 2), which is the expected behavior.  
    Second, put actor at (2, 4) and jumper at (4, 4). After jumper jumps, test whether jumper is at position (2, 4) and actor is removed, which is the expected behavior.

#### Case 5

+ code
    ```java
    /**
     * Case five:
     *   If it encounters another jumper in its path?
     * 
     * Wished Result:
     *   1. Two ceils away
     *          1) Facing the opposite directions, one moves and another turns.
     *          2) Otherwise, both just jump;
     *   1. One ceil away
     *          both just jump;
     */
    @Test
    public void testCaseFive() {
        // Test Result one
        // Rest the world
        reset();
        // two ceils & opposite direction
        world.add(new Location(Number.FOUR, Number.TWO), jumper);
        world.add(new Location(Number.TWO, Number.TWO), jumper2);
        jumper2.setDirection(180);
        jumper2.act();
        jumper.act();
        assertEquals(45, jumper.getDirection());
        assertEquals(jumper, world.getGrid().get(new Location(Number.FOUR, Number.TWO)));
        assertEquals(jumper2, world.getGrid().get(new Location(Number.THREE, Number.TWO)));
        assertEquals(180, jumper2.getDirection());
        // two ceils & different direction
        world.add(new Location(Number.TWO, Number.FOUR), jumper3);
        world.add(new Location(Number.FOUR, Number.FOUR), jumper4);
        jumper3.act();
        jumper4.act();
        assertEquals(jumper3, world.getGrid().get(new Location(0, Number.FOUR)));
        assertEquals(jumper4, world.getGrid().get(new Location(Number.TWO, Number.FOUR)));
        
        // Test Result two
        // Rest the world
        reset();
        jumper.setDirection(0);
        jumper3.setDirection(0);
        jumper4.setDirection(0);
        // one ceil & opposite direction
        world.add(new Location(Number.FOUR, Number.TWO), jumper);
        world.add(new Location(Number.THREE, Number.TWO), jumper2);
        jumper2.setDirection(180);
        jumper2.act();
        jumper.act();
        assertEquals(0, jumper.getDirection());
        assertEquals(jumper, world.getGrid().get(new Location(Number.TWO, Number.TWO)));
        assertEquals(jumper2, world.getGrid().get(new Location(Number.FIVE, Number.TWO)));
        assertEquals(180, jumper2.getDirection());
        // one ceil & different direction
        world.add(new Location(Number.THREE, Number.FOUR), jumper3);
        world.add(new Location(Number.FOUR, Number.FOUR), jumper4);
        jumper4.act();
        jumper3.act();
        assertEquals(jumper3, world.getGrid().get(new Location(Number.ONE, Number.FOUR)));
        assertEquals(jumper4, world.getGrid().get(new Location(Number.TWO, Number.FOUR)));
    }
    ```

+ Analysis  
    First, put jumper at (4, 2) and jumper2 at (2, 2) and turn jumper2 180 degrees to the right. After jumper2 and jumper act, test whether jumper turns 45 degrees and remains its position with jumper2 moves to (3, 2), which is the expected behavior.  
    Second, put jumper3 at (2, 4) and jumper4 at (4, 4). After jumper3 and jumper4 act, test whether jumper3 moves to (0, 4) with jumper4 moves to (2, 4), which is the expected behavior.  
    Third, put jumper at (4, 2) and jumper2 at (3, 2) and turn jumper2 180 degrees to the right. After jumper2 and jumper act, test whether jumper moves to (2, 2) with jumper2 moves to (5, 2), which is the expected behavior.  
    Fourth, put jumper3 at (3, 4) and jumper4 at (4, 4). After jumper3 and jumper4 act, test whether jumper3 moves to (1, 4) with jumper4 moves to (2, 4), which is the expected behavior.

### Result

*Since upload the image in matrix is complex. Set all the test result to be OK.*

![Result](https://s1.ax1x.com/2018/04/21/CMamyn.png)
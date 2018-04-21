# Part 3

<!-- TOC -->

- [Part 3](#part-3)
    - [Usage](#usage)
    - [Jumper](#jumper)

<!-- /TOC -->

## Usage

1. ```ant compile```   
    Compile all the java files into the folders automaticaly layered
1. ```cd class```  
    Move to the class folder.  
1. ```cd Jumper```  
    Choose the jumper to run.
1. ```java JumperRunner```  
    Run the jumper with given test cases.  
    *Test cases refers to the JumperRunner.java file.*

## Jumper

1. What will a jumper do if the location in front of it is empty, but the location two cells in front contains a flower or a rock?
    >+ If called by jump() method, remove the flower or the rock and place the jumper there.
    >+ If called by act() and move() methods, moves one step and leaves a flower in the previous loaction then jumps over it.

1. What will a jumper do if the location two cells in front of the jumper is out of the grid?
    >+ If called by jump() method, remove the jumper as if it jumps out of the grid.
    >+ If called by act() and move() methods, moves one step and leave a flower in the previous loaction.

1. What will a jumper do if it is facing an edge of the grid?
    >+ If called by move() or jump() methods, remove the jumper as if it jumps out of the grid.
    >+ If called by act() method, turn right by 45 degrees until find a path to go.

1. What will a jumper do if another actor (not a flower or a rock) is in the cell that is two cells in front of the jumper?
    >+ If called by jump() methods, remove the actor and place the jumper there.
    >+ If called by act() or move() methods,moves one step.

1. What will a jumper do if it encounters another jumper in its path?
    >1. Two ceils away 
    >       + Facing the opposite directions, one moves one step and another turns 45 to the right.
    >       + Otherwise, both just jump; 
    >1. One ceil away
    >       + both just jump;

1. Are there any other tests the jumper needs to make?
    >+ Can jumper jump over only flowers and rocks?
    >+ What if the in front location is occupied?
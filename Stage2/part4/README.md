# Part 4

<!-- TOC -->

- [Part 4](#part-4)
    - [Preparation](#preparation)
    - [Usage](#usage)
    - [Critters](#critters)
        - [ModifiedChameleonCritter](#modifiedchameleoncritter)
        - [ChameleonKid](#chameleonkid)
        - [BlusterCritter](#blustercritter)
        - [RockHound](#rockhound)
        - [CrabCritter](#crabcritter)
        - [QuickCrab](#quickcrab)
        - [KingCrab](#kingcrab)

<!-- /TOC -->

## Preparation

1. ```Number.jar``` is required.
1. ```gridworld.jar``` is required.

## Usage

1. ```ant compile```   
    Compile all the java files into the folders automaticaly layered
1. ```cd class```  
    Move to the class folder.  
1. ```cd (...)```  
    Choose the Critter you like to run.
1. ```java ()Runner```  
    Run the Critter.

## Critters

### ModifiedChameleonCritter

A <code>ModifiedChameleonCritter</code> takes on the color of neighboring actors as it moves through the grid.

### ChameleonKid

A <code>ChameleonKid</code> takes on the color of neighboring actors in front or behind it as it moves throug the gird.

### BlusterCritter

A <code>BlusterCritter</code> counts the number of critters that occupy grid location within two stpes of this critter's location. If number is fewer than courageFactor, the critter's color brighten, otherwise, darken when it eats and moves

### RockHound

A <code>RockHound</code> looks at a limited set of neighbors and 'eat'(remove) all the rocks when it eats and moves.

### CrabCritter

A <code>CrabCritter</code> looks at a limited set of neighbors when it eats and moves.

### QuickCrab

A <code>QuickCrab</code> moves to empty locations immediately to the right and to the left two locations away if the location and intervening location are both empty.

### KingCrab

A <code>KingCrab</code> looks at a limited set of neighbors and moves neighbors one location away from the KingCrab, otherwise, "eat" (i.e. remove) the actors when it eats and moves.

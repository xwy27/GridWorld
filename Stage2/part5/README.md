# Part 5

<!-- TOC -->

- [Part 5](#part-5)
    - [Usage](#usage)
    - [SparseGrid](#sparsegrid)
        - [SparseBoundedGrid](#sparseboundedgrid)
        - [SparseBoundedGrid1](#sparseboundedgrid1)
        - [UnboundedGrid](#unboundedgrid)

<!-- /TOC -->

## Usage

1. ```ant compile```   
    Compile all the java files into the folders automaticaly layered
1. ```cd class```  
    Move to the class folder.  
1. ```java SparseGridRunner```  
    Run the runner.

*For UnboundedGrid2 test, choose the gird shown by UnboundedGrid2*  
*Then move the critter to a loaction outside the grid.*  
*After that, reset the grid to be shown by UnboundedGrid2.*  
*You will find the critter shows up in the new location and gird size is doubled.*

## SparseGrid

### SparseBoundedGrid

A <code>SparseBoundedGrid</code> is a rectangular grid with bounded edges and sparse objects in the gird implemented by 'sparse array'.

### SparseBoundedGrid1

A <code>SparseBoundedGrid</code> is a rectangular grid with bounded edges and sparse objects in the gird implemented by HashMap.

### UnboundedGrid

An <code>UnboundedGrid</code> is a rectangular grid with an unbounded number of rows and columns which will double its size value when access over the current size.
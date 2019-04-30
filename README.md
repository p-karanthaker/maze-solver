# Maze Solver
A program to solve simple mazes. The mazes are given in a file and the program must read in the 
file, solve the maze, and output the solution.

## Table of Contents
1. [Maze Input/Output Formats](#maze-inputoutput-formats)
2. [Maze Input Format](#input-format)
3. [Maze Output Format](#output-format)
4. [Movement](#movement)
5. [Horizontal Wrapping](#horizontal)
6. [Vertical Wrapping](#vertical)


## Maze Input/Output Formats
### Input Format
A plain-text file where:
* 1 - denotes walls
* 0 - denotes passageways

The file is laid out like so:

```
<WIDTH> <HEIGHT><CR>
<START_X> <START_Y><CR>
<END_X> <END_Y><CR>
... grid of 0's and 1's
```

#### Example
A 10x10 grid with starting coordinates (1,1), and ending coordinates (8,8).
```
10 10
1 1
8 8
1 1 1 1 1 1 1 1 1 1
1 0 0 0 0 0 0 0 0 1
1 0 1 0 1 1 1 1 1 1
1 0 1 0 0 0 0 0 0 1
1 0 1 1 0 1 0 1 1 1
1 0 1 0 0 1 0 1 0 1
1 0 1 0 0 0 0 0 0 1
1 0 1 1 1 0 1 1 1 1
1 0 1 0 0 0 0 0 0 1
1 1 1 1 1 1 1 1 1 1
```

### Output Format
The output is a representation of the maze with a marked path from start to end where:
* \# - denotes a wall
* whitespace - denotes a passageway
* X - denotes the path
* S - denotes the start
* E - denotes the end

#### Example
The solution to the 10x10 grid example.
```
##########
#SXX     #
# #X######
# #XX    #
# ##X# ###
# # X# # #
# # XX   #
# ###X####
# #  XXXE#
##########
```

## Movement
Valid moves are N, S, E, W only, with no diagonal movement allowed. Moves in any of these directions 
will be blocked by maze walls, which are identified as 1's in the input file. The edges of the grid 
should not be considered as walls unless specified, and allow for wrapping movement.

### Wrapping Example
#### Horizontal
##### Input
```

10 10
1 1
8 8
1 1 1 1 1 1 1 1 1 1 
0 0 1 0 0 0 0 0 0 0 
0 0 1 0 0 1 1 1 1 1 
1 0 1 0 0 0 0 0 0 1 
1 0 1 1 0 1 0 1 1 1 
1 0 1 0 0 1 0 1 0 1 
1 0 1 0 0 0 0 0 0 1 
1 0 1 0 0 0 1 1 1 1 
1 0 1 0 0 0 0 0 0 1 
1 1 1 1 1 1 1 1 1 1 
```
##### Output
```
##########
XS# XXXXXX
  # X#####
# # X    #
# ##X# ###
# # X# # #
# # X    #
# # X ####
# # XXXXE#
##########
```

#### Vertical
##### Input
```
10 10
1 1
8 8
1 0 1 1 1 1 1 1 1 1 
1 0 1 0 0 0 0 0 0 0 
1 0 1 0 1 1 1 1 1 1 
1 1 1 0 0 0 0 0 0 1 
1 0 1 1 0 1 0 1 1 1 
1 0 1 0 0 1 0 1 0 1 
1 0 1 0 0 0 0 0 0 1 
1 0 1 1 1 0 1 1 1 1 
1 0 0 0 0 0 0 0 0 1 
1 0 1 1 1 1 1 1 1 1 
```
##### Output
```
#X########
#S#       
# # ######
###      #
# ## # ###
# #  # # #
# #      #
# ### ####
#XXXXXXXE#
#X########
```
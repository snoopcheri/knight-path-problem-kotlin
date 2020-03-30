# Knight Moves Problem Utility

This project offers a utility to calculate the minimum number of knight moves
it takes in order to get from a specific starting position to specific end position.

For given set of allowed squares, starting position and end position, the utility outputs:
* the number of (distinct) boards for the given configuration
* the minimum number of moves to transpose the starting position to the end position
* a path of moves for the minimum number of moves, where moves which move the same knight
  several times are preferred (because it's easier to follow the solution)

The tool does not print out all variations because those tend to be extremely large due
to transpositions (that is move A and then B leading to the same position as move B and then A)


## Rules of the game

* it doesn't matter whether a white or a black knight is moved
* colours also do not have to switch - it's perfectly legal to move the same knight several times in a row


## The utility supports

* arbitrary chess board sizes up to size 8x8
* the board can also contain holes
* basically you can configure which squares of a full 8x8 chess board are allowed
* you can specify the number of knights for each side
* the start and end position you want to check obviously has to use the same number of knights... ^^


##

The utilty works by starting with the desired end position and then backtracking.

* all possible boards are generated
  * for Johannes' board: # = (10 * 9 * 8 * 7) = 1260 distinct positions
  * for two-knights problem on full board: # = (64 * 63 * 62 * 61) / 4 = 3_812_256 distinct positions

* a map is created, where the key is a distinct position and the value is the number of moves
  in which this position can be reached
* this map is initialized with the end position and has value 0
* then all not yet solved positions are checked whether there is a move which reaches
  a position that is solved
  In general: if position X has a move which leads to position Y, which can be reached in N moves,
              we know that position X can be reached in (N+1) moves
* we do this untill all positions are solved

Btw: it's possible that there are positions which cannot be reached

## Game Results

### Johannes-problem

This problem only has 10 allowed squares where the knights can move to:
* A1
* B1, B2, B3, B4
* C1, C2, C3
* D1, D2

In the starting position, there are:
* two black knights (denoted by 'n') at A1 and C1
* two white knights (denoted by 'N') at C2 and B4

The end position is the starting position with colour-swapped knights.

From board:
```
4   N
3   . .
2   . N .
1 n . n .
  a b c d
```


To board:
```
4   n
3   . .
2   . n .
1 N . N .
  a b c d
```

Solution:
Number of steps required = 40
```
[
    a1-b3, b3-d2, d2-b1, b1-c3,
    c2-a1, a1-b3, b3-d2, d2-b1,
    c1-b3, b3-d2,
    b4-c2, c2-a1, a1-b3, b3-c1,
    d2-b3, b3-a1, a1-c2, c2-b4,
    b1-d2, d2-b3, b3-a1, a1-c2,
    c3-b1, b1-d2, d2-b3, b3-a1,
    c1-b3, b3-d2, d2-b1
    a1-b3, b3-d2
    c2-a1, a1-b3, b3-c1
    d2-b3, b3-a1, a1-c2
    b1-d2, d2-b3, b3-a1
]
```

### Four-Knights problem on standard board

This problem uses the whole chess board and the white and black knights
are placed on their initial position.

The end position is the starting position with colour-swapped knights.

From board:
```
8 . n . . . . n .
7 . . . . . . . .
6 . . . . . . . .
5 . . . . . . . .
4 . . . . . . . .
3 . . . . . . . .
2 . . . . . . . .
1 . N . . . . N .
  a b c d e f g h
```

To board:
```
8 . n . . . . n .
7 . . . . . . . .
6 . . . . . . . .
5 . . . . . . . .
4 . . . . . . . .
3 . . . . . . . .
2 . . . . . . . .
1 . N . . . . N .
  a b c d e f g h
```

Solution:
Number of steps required = 16
```
[
    b1-c3, c3-d5, d5-e7,
    g1-e2, e2-d4, d4-c6,
    b8-d7, d7-e5, e5-f3, f3-g1,
    c6-b8,
    g8-f6, f6-e4, e4-d2, d2-b1,
    e7-g8
]
```

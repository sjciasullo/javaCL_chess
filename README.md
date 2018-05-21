# READ_ME

- [Project Spec](https://git.generalassemb.ly/SECA/project-I/tree/master)
- Command Line Chess

### Technologies Used
- Java, Object Oriented Principles and design, IntelliJ Idea

### Approach
1. Phase 1 - Design
    - general notes
        - structures
            - board:
                - 2x2 array of tiles
                - uses a hashmap of codes to 2d array spot
        - makeMove
            1. read in move
            1. first check if it actually is a position on the board
            2. isValidMove(Piece, Board)
                - check to see player has the moving piece at that location
                - different ways of calculating from that end to original spot, rather than finding piece in player.team and calulating end place from there
                - checks possible moves for piece against whether or not full?
                - no, first check if spot if full
                    - after, make a dummy piece of that kind and try to move to it?
                    - or use a second hashmap to hold pieces

            3. if new spot is valid, and contains a piece from other team, remove piece from other team
        - checkmate considerations
            - will need to use scan after moving a piece to check if it should be added to checkList
            - if added to checkList will need to have each possible move of checkers
            - if king of opposite team has no possible moves out then checkmate
            - possible strategy
                1. test if king can move out of check
                    - check all locations for king
                        - check if any opposing team pieces can move to that spot
                2. test if any of king's pieces can take the checker's position
                3. if checker is blockable, test if any of king's pieces can move to a blocking position (between king and checker)
                4. then return checkmate... 
                5. doesn't account for double check though
    - Class Organization
        - Abstract class Piece
            - static left bound and right bound for checking canMoveToposition
            - Pawn, Rook, Knight, Bishop, Queen, King inherit from Piece
            - Coordinate position
                - denotes position in board array
                - get, set
            - boolean isFirstMove
                - for kings and pawns really to see if they can move 2 or castle
                - get, does not need set because if canMove is called will be changed internally?
            - String team
                - created on construction to be black or white
                - get, no set because on construction
            - getBoardName() returns string of full name for board
            - ~~canMoveToPosition~~isValidMove(String[][] board, Coordinate position)
                - checks if a piece can move to a specific position
                - ~~does this return string of piece if occupied?~~ no, returns boolean, obviously, so will have to check in moveFunction after canMove... is called
                - ~~possibly may have to use int returns so that we can return the reverse for moving?~~
                    - this is solved by changing command structure to "position to position" and this also supports using hashmap<position, piece>
            - possibles
                - stubbed canCheck(Coordinate position)
                    - potential / check helper
                - stubbed scan()
                    - may need to use a scan for checking purposes
                - boolean isBlockable for checkmate consideration (true for queen, bishop, rook)
        - GameState
            - Piece[] white, black
                - may change this and make class Team to hold team and checkers
                    - this method may be more difficult to delete members from team
                    - maybe it is a hashmap with pieceName, array of pieces second
                    - maybe it is a hashmap by position, then I can just delete by position in constant time
                - could create in order, but want to maintain kings position so should be 0...
                - teams are created in construction
                - no get, set managed within class
            - String[][] board
                - teams are added to at proper time
                - if adding team is loop could create Pieces in order to be added
                - no get, set managed within class
            - final static COORD_TO_ARRAY
                - hash for getting command coordinate and converting to array coordinate
            - printBoard()
                - uses board to print a formatted chess board
                - uses length of word and offsets from correct middle position to substitute a name in for spaces at that point
                - this method might create blank board and then read through team pieces

            

2. Phase 2 - Implementation of Object Structures
    1. create Pieces superclass
    2. create Pawn class
    3. Test pawn class creation
    2. create GameState object
    4. create teams of pawns
    3. print function
        1. empty board
        2. print team of pawns
    4. check movement of pawns using functions, ensure proper printing
    5. repeat for implementation of other game pieces
3. Phase 2.1 - Redesign as implementing
4. Phase 2.2 - Test new basics of feature
4. Phase 3 add printing and reading interface
5. Phase 4 add game logic of winning conditions


### Installation Instructions
    1. install Java 8 sdk 1.8
    2. fork or clone repository
    3. navigate to repo within command line interface
    4. compile Main.java
    5. execute compiled main

### Unsolved Problems
- ~~pawn does not resolve movement to take another pawn~~
    - how to replicate error: move opponent pawn to position where it can be taken
        - try to take pawn by entering origin and destination
        - error holds for both teams
    - ~~is the board[][] being updated correctly?~~
    - ~~is it a problem with the pawn isvalidmove function?~~
-   solution: breakpoint on isValidMove in pawn class
    - this showed that current location of piece was not updated yet. this indicates a problem within GameState.runGame as we need to update the location in the hashmap of team after making a move
    - Success
- refactoring, reducing repetitive code in isValidMove and elsewhere
    - ~~might be able to refactor with a function for checking the different possible lines~~
    - use a function for calculating slope in queen and bishop
    - use line checker for rook
    - bring occupant check out to gameState so it doesn't have to be checked in every piece
    - can we cut down on how the teams are made?
    - runGame is very cluttered and long... create function to handle make move so it's not the exact same code for different teams. could create Team class and make array of teams
- solve checkmate checking, double check, and stalemate
    - use Piece.canCheck()?
    - keep a King.isChecked
    - King.scanForMoves
    - if use strategy then how do we handle double checks?
    - temporary solution:
        - ~~add a forfeit command option~~
        - ~~maintain a coordinate for blackKing and whiteKing~~
            - ~~if the king moves update it~~
        - ~~if the piece that moved can move to king location, notify check~~
            - ~~save checker location in variable and go to check gamestate~~
            - ~~if a command is entered that is a valid move, see if anyone can still attack the king. if they can then prompt the user that they would still be in check and that they can forfeit if they want~~
            - still need to implement being able to put yourself in check, so need to check if other team can check current after EVERY move -- this should also be a method
- desired command structure is "piece_name to location"
    - can we make this implementation work?
    - first changed to location to location to account for ease of checking if team has piece and it can move there, using hashmap structure<location, piece>
- found bug in trying to move queen
    - should have thrown error so double check that abs value of slope is 1 in line check

### Future Features
- optional 1 player or 2 player
    - creation of chess AI
- adding visuals
- castling
- automatic checkmate finish
- different command structure

### Wins and Challenges
-   Design for such a big project with a lot of moving parts. Being able to visualize all the parts with the whole, knowing what way to organize the data in order to solve problems during design phase.
- After being brought to the problem of using an array as a hash key, I was able to create a class of Coordinate objects for the hash. The meant I had to override the equals and hashCode methods for the Coordinate class which brought me to a deeper understanding of overriding a Java-defined method and Java's implementation of those Object methods.
- successful debugging of pawn movement taking another pawn
- using lambda function and wrapper class to loop through hashmap
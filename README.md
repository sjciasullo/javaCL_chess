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
            - static left bound and right bound for checking canMoveToLocation
            - Pawn, Rook, Knight, Bishop, Queen, King inherit from Piece
            - int[] position
                - denotes position in board array
                - get, set
            - boolean isFirstMove
                - for kings and pawns really to see if they can move 2 or castle
                - get, does not need set because if canMove is called will be changed internally?
            - String team
                - created on construction to be black or white
                - get, no set because on construction
            - getBoardName() returns string of full name for board
            - canMoveToLocation(String[] board, int[] location)
                - checks if a piece can move to a specific location
                - ~~does this return string of piece if occupied?~~ no, returns boolean, obviously, so will have to check in moveFunction after canMove... is called
                - ~~possibly may have to use int returns so that we can return the reverse for moving?~~
                    - this is solved by changing command structure to "position to position" and this also supports using hashmap<position, piece>
            - possibles
                - stubbed canCheck(int[] location)
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
- solve checkmate checking
    - use Piece.canCheck()?
    - keep a King.isChecked
    - King.scanForMoves
    - if use strategy then how do we handle double checks?
- desired command structure is "piece_name to location"
    - can we make this implementation work?
    - first changed to location to location to account for ease of checking if team has piece and it can move there, using hashmap structure<location, piece>


### Wins and Challenges
-   Design for such a big project with a lot of moving parts. Being able to visualize all the parts with the whole, knowing what way to organize the data in order to solve problems during design phase.

package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class GameState {
  private HashMap<Coordinate, Piece> white = new HashMap<>();
  private HashMap<Coordinate, Piece> black = new HashMap<>();
  private String[][] board = new String[8][8]; // chessboard
  private HashMap<String, Coordinate> printToCoord = new HashMap<>();

  // Print helpers
  private final int WIDTH = 107;
  private final int HEIGHT = 5*8 + 2;
  private final int COLUMN_WIDTH = 13;
  private final int ROW_HEIGHT = 5;
  private final String NEW_LINE = System.lineSeparator();
  private String letterHeader;
  private String horizontalLine;
  private String spaces;

  GameState(){
    // Create both teams
    // -- create pawns
    for(int i = 0; i < 8; i++){
      Coordinate blackPosition = new Coordinate(1, i);
      black.put(blackPosition, new Pawn("black", blackPosition));

      Coordinate whitePosition = new Coordinate(6, i);
      white.put(whitePosition, new Pawn("white", whitePosition));
    }
    // -- create rooks
    black.put(new Coordinate(0,0), new Rook("black", new Coordinate(0,0)));
    black.put(new Coordinate(0,7), new Rook("black", new Coordinate(0,7)));
    white.put(new Coordinate(7,0), new Rook("white", new Coordinate(7,0)));
    white.put(new Coordinate(7,7), new Rook("white", new Coordinate(7,7)));
    // -- create knights
    black.put(new Coordinate(0,1), new Knight("black", new Coordinate(0,1)));
    black.put(new Coordinate(0,6), new Knight("black", new Coordinate(0,6)));
    white.put(new Coordinate(7,1), new Knight("white", new Coordinate(7,1)));
    white.put(new Coordinate(7,6), new Knight("white", new Coordinate(7,6)));
    // -- create bishops
    black.put(new Coordinate(0,2), new Bishop("black", new Coordinate(0,2)));
    black.put(new Coordinate(0,5), new Bishop("black", new Coordinate(0,5)));
    white.put(new Coordinate(7,2), new Bishop("white", new Coordinate(7,2)));
    white.put(new Coordinate(7,5), new Bishop("white", new Coordinate(7,5)));
    // -- create kings
    black.put(new Coordinate(0,4), new King("black", new Coordinate(0,4)));
    white.put(new Coordinate(7,4), new King("white", new Coordinate(7,4)));
    // -- create queens
    black.put(new Coordinate(0,3), new Queen("black", new Coordinate(0,3)));
    white.put(new Coordinate(7,3), new Queen("white", new Coordinate(7,3)));

    // Initialize board
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        Coordinate key = new Coordinate(i, j);
        if(black.containsKey(key)){
          board[i][j] = black.get(key).getBoardName();
        } else if(white.containsKey(key)){
          board[i][j] = white.get(key).getBoardName();
        } else {
          board[i][j] = "";
        }
      }
    }

    // Create Print String Helpers
      // Header
    StringBuilder headerBuilder = new StringBuilder(WIDTH);
    int asciiLetter = 65; // => 'A'
    for(int i = 0; i < WIDTH; i++){
      if((i - 8) % COLUMN_WIDTH == 0){
        headerBuilder.append((char) asciiLetter);
        asciiLetter++;
      } else {
        headerBuilder.append(" ");
      }
    }
    headerBuilder.append(NEW_LINE);
    letterHeader = headerBuilder.toString();

      // Horizontal Line
    StringBuilder horizontalBuilder = new StringBuilder(WIDTH);
    horizontalBuilder.append("  ");
    for(int i = 2; i < WIDTH; i++){
      if((i - 2) % COLUMN_WIDTH == 0){
        horizontalBuilder.append("|");
      } else {
        horizontalBuilder.append("_");
      }
    }
    horizontalBuilder.append(NEW_LINE);
    horizontalLine = horizontalBuilder.toString();

      // spaces
    StringBuilder spaceBuilder = new StringBuilder(WIDTH);
    for(int i =0; i < WIDTH; i++){
      if((i - 2) % COLUMN_WIDTH == 0){
        spaceBuilder.append("|");
      } else {
        spaceBuilder.append(" ");
      }
    }
    spaceBuilder.append(NEW_LINE);
    spaces = spaceBuilder.toString();

    // create map for visual coordinates to coordinates for string[][] board
    // A8 = [0][0]...
    asciiLetter = 65; // set back to A
    for(int coordRow = 0, printRow = 8; coordRow < 8; coordRow++, printRow--){
      for(int coordColumn = 0; coordColumn < 8; coordColumn++){
        printToCoord.put((char) (asciiLetter + coordColumn) + Integer.toString(printRow), new Coordinate(coordRow, coordColumn));
      }
    }
  }

  public void runGame(){
    // print welcome message
    System.out.println("Welcome to Command Line Chess!");
    System.out.println("\nIf you are unfamiliar with the rules, please visit http://www.dummies.com/games/chess/chess-for-dummies-cheat-sheet/");
    System.out.println("\nPlease enter 'play' to begin");
    System.out.println("Enter 'help' at any time to see correct move formatting\n");

    Scanner input = new Scanner(System.in);
    String newLine;
    String[] lineWords;

    boolean playBegin = false;
    while(!playBegin){
      newLine = input.nextLine().toLowerCase();
      lineWords = newLine.split(" ");
      if(lineWords[0].equals("play")){
        playBegin = true;
      }
    }

    System.out.println("You don't suppose this is going to be like [slight pause] real wizard's chess, do you?\n");

    //runGame until we have a winner
    boolean checkMate = false;
    boolean checked = false;
    Coordinate checker = null;
    Coordinate whiteKing = new Coordinate(7, 4);
    Coordinate blackKing = new Coordinate(0, 4);

    String currentTeam = "White";
    while(!checkMate){
      printBoard();
      System.out.println();

      // read in a move
      boolean validMoveEntered = false;
      while(!validMoveEntered){
        System.out.print(currentTeam + " team's turn.\n" + (checked ? "You are in check! Get out or forfeit! " : "") + "Please enter a move: ");
        newLine = input.nextLine().toUpperCase();
        lineWords = newLine.split(" ");

        if(lineWords[0].equals("HELP")){
          printHelp();
        } else if(lineWords[0].equals("FORFEIT")){
          validMoveEntered = true;
          System.out.println(currentTeam + " forfeits!");
          String winning = currentTeam.equals("White")? "Black" : "White";
          System.out.println(winning + " team is the winner!");
          System.out.println("(That was exactly like wizard's chess. . .)");
          checkMate = true;
        } else if(lineWords.length < 2) {
          System.out.println("You have entered an invalid move. Please try again.");
        } else if(isValidCommand(lineWords)){
          Coordinate origin = printToCoord.get(lineWords[0]);
          Coordinate destination = printToCoord.get(lineWords[1]);

          // check if current team has a piece at that spot
          if(currentTeam.equals("White") && white.containsKey(origin)){
            if(white.get(origin).isValidMove(board, destination)){
              if(checked){
                // if you are trying to kill checker, temporarily delete from opponent's team
                boolean checkerRemoved = false;
                Piece checkerPiece;
                if(destination.equals(checker)){
                  checkerPiece = black.remove(checker);
                  checkerRemoved = true;
                } else {
                  checkerPiece = null;
                }
                // if you are moving king, set checker to new location
                Coordinate kingPosition;
                String[] name = white.get(origin).getBoardName().split(" ");
                if(name[1].equals("king")){
                  kingPosition = new Coordinate(destination.getRow(), destination.getColumn());
                } else {
                  kingPosition = whiteKing;
                }

                // check if anyone can check you in remaining
                // solution: https://javatutorial.net/java-iterate-hashmap-example
                // wrapper solution: https://stackoverflow.com/questions/30026824/modifying-local-variable-from-inside-lambda
                BooleanWrapper unchecker = new BooleanWrapper();
                black.forEach((key, value) -> {
                  if(value.isValidMove(board, kingPosition)){
                    unchecker.setToFalse(); // if set to false then the move does not get team out of check
                  }
                });
                //return temporary remove
                if(checkerRemoved){
                  black.put(checker, checkerPiece);
                }

                //if successfully unchecked, proceed with regular move
                // copying from lines validMoveEntered to end of if(checked) TODO: refactor into function
                if(unchecker.getUncheck()){
                  checked = false;
                  checker = null;
                  validMoveEntered = true;
                  // if opponent had piece at origin, remove it from the team
                  if(white.containsKey(destination)){
                    Piece defeated = black.remove(destination);
                    System.out.println(white.get(origin).getBoardName() + " takes " + defeated.getBoardName());
                  }

                  // move the piece to new destination and update board, and update Piece.currentPosition
                  white.put(destination, white.get(origin));
                  white.remove(origin);
                  white.get(destination).setPosition(destination);
                  board[destination.getRow()][destination.getColumn()] = white.get(destination).getBoardName();
                  board[origin.getRow()][origin.getColumn()] = "";
                  // update king if necessary
                  if(name[1].equals("king")){
                    whiteKing = new Coordinate(destination.getRow(), destination.getColumn());
                  }
                  // check if moved piece can attack king
                  checked = white.get(destination).isValidMove(board, whiteKing);
                  if(checked){
                    checker = new Coordinate(destination.getRow(), destination.getColumn());
                  }
                } else {
                  System.out.println("This move does not get you out of check.");
                  System.out.println("Please try another move or type 'forfeit' to surrender your king.");
                }
                // end check implementation
              } else {
                validMoveEntered = true;
                // if opponent had piece at origin, remove it from the team
                if(black.containsKey(destination)){
                  Piece defeated = black.remove(destination);
                  System.out.println(white.get(origin).getBoardName() + " takes " + defeated.getBoardName());
                }

                // move the piece to new destination and update board, AND UPDATE PIECE.CURRENTposition
                white.put(destination, white.get(origin));
                white.remove(origin);
                white.get(destination).setPosition(destination);
                board[destination.getRow()][destination.getColumn()] = white.get(destination).getBoardName();
                board[origin.getRow()][origin.getColumn()] = "";
                // update king if necessary
                String[] name = white.get(destination).getBoardName().split(" ");
                if(name[1].equals("king")){
                  whiteKing = new Coordinate(destination.getRow(), destination.getColumn());
                }
                // check if moved piece can attack king
                checked = white.get(destination).isValidMove(board, blackKing);
                if(checked){
                  checker = new Coordinate(destination.getRow(), destination.getColumn());
                }
              }
            }
          } else if(black.containsKey(origin)){
            if(black.get(origin).isValidMove(board, destination)){
              if(checked){
                // if you are trying to kill checker, temporarily delete from opponent's team
                boolean checkerRemoved = false;
                Piece checkerPiece;
                if(destination.equals(checker)){
                  checkerPiece = white.remove(checker);
                  checkerRemoved = true;
                } else {
                  checkerPiece = null;
                }
                // if you are moving king, set checker to new location
                Coordinate kingPosition;
                String[] name = black.get(origin).getBoardName().split(" ");
                if(name[1].equals("king")){
                  kingPosition = new Coordinate(destination.getRow(), destination.getColumn());
                } else {
                  kingPosition = blackKing;
                }

                // check if anyone can check you in remaining
                // solution: https://javatutorial.net/java-iterate-hashmap-example
                // wrapper solution: https://stackoverflow.com/questions/30026824/modifying-local-variable-from-inside-lambda
                BooleanWrapper unchecker = new BooleanWrapper();
                white.forEach((key, value) -> {
                  if(value.isValidMove(board, kingPosition)){
                    unchecker.setToFalse(); // if set to false then the move does not get team out of check
                  }
                });
                // return temporarily removed piece
                if(checkerRemoved){
                  white.put(checker, checkerPiece);
                }

                //if successfully unchecked, proceed with regular move
                // copying from lines validMoveEntered to end of if(checked) TODO: refactor into function
                if(unchecker.getUncheck()){
                  checked = false;
                  checker = null;
                  validMoveEntered = true;
                  // if opponent had piece at origin, remove it from the team
                  if(white.containsKey(destination)){
                    Piece defeated = white.remove(destination);
                    System.out.println(black.get(origin).getBoardName() + " takes " + defeated.getBoardName());
                  }

                  // move the piece to new destination and update board, and update Piece.currentPosition
                  black.put(destination, black.get(origin));
                  black.remove(origin);
                  black.get(destination).setPosition(destination);
                  board[destination.getRow()][destination.getColumn()] = black.get(destination).getBoardName();
                  board[origin.getRow()][origin.getColumn()] = "";
                  // update king if necessary
                  if(name[1].equals("king")){
                    blackKing = new Coordinate(destination.getRow(), destination.getColumn());
                  }
                  // check if moved piece can attack king
                  checked = black.get(destination).isValidMove(board, whiteKing);
                  if(checked){
                    checker = new Coordinate(destination.getRow(), destination.getColumn());
                  }
                } else {
                  System.out.println("This move does not get you out of check.");
                  System.out.println("Please try another move or type 'forfeit' to surrender your king.");
                }
                // end check implementation
              } else {
                validMoveEntered = true;
                // if opponent had piece at origin, remove it from the team
                if(white.containsKey(destination)){
                  Piece defeated = white.remove(destination);
                  System.out.println(black.get(origin).getBoardName() + " takes " + defeated.getBoardName());
                }

                // move the piece to new destination and update board, and update Piece.currentPosition
                black.put(destination, black.get(origin));
                black.remove(origin);
                black.get(destination).setPosition(destination);
                board[destination.getRow()][destination.getColumn()] = black.get(destination).getBoardName();
                board[origin.getRow()][origin.getColumn()] = "";
                // update king if necessary
                String[] name = black.get(destination).getBoardName().split(" ");
                if(name[1].equals("king")){
                  blackKing = new Coordinate(destination.getRow(), destination.getColumn());
                }
                // check if moved piece can attack king
                checked = black.get(destination).isValidMove(board, whiteKing);
                if(checked){
                  checker = new Coordinate(destination.getRow(), destination.getColumn());
                }
              }
            }
          }

          // if all of the above conditions didn't fit exactly
          if(!validMoveEntered){
            System.out.println("You have entered an invalid move. Please try again.");
            printHelp();
          }

        } else {
          System.out.println("You have entered an invalid move. Please try again.");
          printHelp();
        }
      }

      // switch active team
      currentTeam = currentTeam.equals("White") ? "Black" : "White";
    }

    // print winner and winning message
    input.close();
  }

  // PRIVATE METHODS FOR RUNGAME()

  // Prints the board using strings created at construction and the String[][] board
  // sizing of print board is to allow for space around any piece's name
  private void printBoard(){
    StringBuilder printBuilder = new StringBuilder(WIDTH * HEIGHT);
    int rowDisplay = 8;
    int boardRow = 0;
    for(int row = 0; row < HEIGHT; row++){
      if(row == 0){
        printBuilder.append(letterHeader);
      } else if((row - 1) % ROW_HEIGHT == 0){
        printBuilder.append(horizontalLine);
      } else if((row - 4) % ROW_HEIGHT == 0){
        // middle of each row check for a string in String[][] board
        StringBuilder pieceRow = new StringBuilder(spaces);
        pieceRow.replace(0, 1, Integer.toString(rowDisplay)); // display the row for visual on board
        rowDisplay--;
        for(int printColumn = 4, boardColumn = 0; printColumn < WIDTH; printColumn += COLUMN_WIDTH, boardColumn++){
          String boardContents = board[boardRow][boardColumn];
          if(!boardContents.equals("")){
            pieceRow.replace(printColumn, boardContents.length() + printColumn, boardContents);
          }
        }
        boardRow++;
        printBuilder.append(pieceRow);
      } else {
        printBuilder.append(spaces);
      }
    }
    System.out.println(printBuilder.toString());
  }

  private void printHelp(){
    System.out.println("An example of a valid move is 'A2 A3'.");
    System.out.println("For '[origin] [destination]' you must own the piece at origin and it must be able to move to destination under the rules of chess.");
    System.out.println("If you are unfamiliar with the rules, please visit http://www.dummies.com/games/chess/chess-for-dummies-cheat-sheet/");
    System.out.println();
  }

  // returns true if both strings are valid keys in the coordinate converter
  private boolean isValidCommand(String[] commands){
    return printToCoord.containsKey(commands[0]) && printToCoord.containsKey(commands[1]) &&
           !(commands[0].equals(commands[1]));
  }
}

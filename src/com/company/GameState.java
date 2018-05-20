package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class GameState {
  private HashMap<Coordinate, Piece> white = new HashMap<>();
  private HashMap<Coordinate, Piece> black = new HashMap<>();
  private String[][] board = new String[8][8]; // chessboard

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
      System.out.println(Arrays.toString(lineWords));
      if(lineWords[0].equals("play")){
        playBegin = true;
      }
    }

    System.out.println("You don't suppose this is going to be like [slight pause] real wizard's chess, do you?\n");
    printBoard();

    //runGame until we have a winner
    boolean checkMate = false;
    String winningTeam = "";
    String currentTeam = "White";
    while(!checkMate){
      printBoard();
      System.out.println();
      boolean validMoveEntered = false;
      while(!validMoveEntered){
        System.out.print(currentTeam + " team's turn.\n" + "Please enter a move: ");
        newLine = input.nextLine().toUpperCase();
        lineWords = newLine.split(" ");
        if(lineWords[0].equals("HELP")){
          printHelp();
          // else check if both first and second words are valid keys in map, else print error
        } /*else if(){

        } */else {
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

  // returns false if we need a invalid command or help is entered
  private boolean isValidCommand(){
    return false;
  }
}

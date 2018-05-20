package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class GameState {
  private HashMap<Coordinate, Piece> white = new HashMap<>();
  private HashMap<Coordinate, Piece> black = new HashMap<>();
  private String[][] board = new String[8][8]; // chessboard

  // Print helpers
  private final int WIDTH = 106;
  private final int HEIGHT = 58;
  private final int COLUMN_WIDTH = 13;
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
    StringBuffer headerBuffer = new StringBuffer(WIDTH);
    int asciiLetter = 65; // => 'A'
    for(int i = 0; i < WIDTH; i++){
      if((i - 7) % COLUMN_WIDTH == 0){
        headerBuffer.append((char) asciiLetter);
        asciiLetter++;
      } else {
        headerBuffer.append(" ");
      }
    }
    headerBuffer.append(NEW_LINE);
    letterHeader = headerBuffer.toString();

      // Horizontal Line
    StringBuffer horizontalBuffer = new StringBuffer(WIDTH);
    horizontalBuffer.append(" ");
    for(int i = 1; i < WIDTH; i++){
      if((i - 1) % COLUMN_WIDTH == 0){
        horizontalBuffer.append("|");
      } else {
        horizontalBuffer.append("_");
      }
    }
    horizontalBuffer.append(NEW_LINE);
    horizontalLine = horizontalBuffer.toString();

      // spaces
    spaces = "";
    String
    for(int i =0; i < WIDTH; i++){
      if((i - 1) % COLUMN_WIDTH == 0){
        spaces += "|";
      } else {
        spaces += " ";
      }
    }
    spaces += NEW_LINE;
  }

  public void runGame(){
    boolean isWinner = false;
    String winningTeam = "";

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

    System.out.println("You don't suppose this is going to be like [slight pause] real wizard's chess, do you?");

    //runGame until we have a winner
    /*
    while(!isWinner){

    }
    */
  }

  // PRIVATE METHODS FOR RUNGAME()

  // sizing of print board is to allow for space around any piece's name
  private void printBoard(){
  }

  // returns false if we need a invalid command or help is entered
  private boolean isValidCommand(){
    return false;
  }
}

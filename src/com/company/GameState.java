package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class GameState {
  private HashMap<Coordinate, Piece> white = new HashMap<>();
  private HashMap<Coordinate, Piece> black = new HashMap<>();
  private String[][] board = new String[8][8]; // chessboard

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
  }

  public void runGame(){
    boolean isWinner = false;
    String winningTeam = "";

    // print welcome message
    System.out.println("Welcome to Command Line Chess!");
    System.out.println("\nIf you are unfamiliar with the rules, please visit http://www.dummies.com/games/chess/chess-for-dummies-cheat-sheet/");
    System.out.println("\nPlease enter 'play' to begin");
    System.out.println("Enter 'help' at any time to see correct move formatting");

    

    //runGame until we have a winner
    /*
    while(!isWinner){

    }
    */
  }
}

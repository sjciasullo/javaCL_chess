package com.company;

import java.util.HashMap;

public class GameState {
  private HashMap<int[], Piece> white = new HashMap<>();
  private HashMap<int[], Piece> black = new HashMap<>();
  private String[][] board = new String[8][8]; // chessboard

  GameState(){
    // Create both teams
    // -- create pawns
    for(int i = 0; i < 8; i++){
      int[] blackPosition = {1, i};
      black.put(blackPosition, new Pawn("black", blackPosition));

      int[] whitePosition = {6, i};
      white.put(whitePosition, new Pawn("white", whitePosition));
    }

    System.out.println(black.keySet());
    System.out.println(white.keySet());

    int[] keys = {1, 0};
    System.out.println(Boolean.toString(black.containsKey(keys)));

    // Initialize board
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        int[] key = {i, j};
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
}

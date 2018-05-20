package com.company;

public class Main {

  public static void main(String[] args) {
    // TESTING CLASS METHODS AND PROPERTIES

    // PAWN
    Coordinate position = new Coordinate(1,2);
    Pawn pawn1 = new Pawn("black", position);
    System.out.println(pawn1.getBoardName());

    // BEGIN GAMESTATE
    //GameState game = new GameState();
  }
}

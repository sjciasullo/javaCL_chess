package com.company;

public class Main {

  public static void main(String[] args) {
    // TESTING CLASS METHODS AND PROPERTIES

    // PAWN
    int[] position = {1,2};
    Pawn pawn = new Pawn("black", position);
    System.out.println(pawn.getBoardName());

  }
}

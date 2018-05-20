package com.company;

public class Rook extends Piece{
  Rook(String team, Coordinate position){
    super(team, position);
  }

  public String getBoardName(){
    return "(" + this.getTeam().charAt(0) + ")" + " rook";
  }
}

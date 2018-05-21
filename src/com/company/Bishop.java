package com.company;

public class Bishop extends Piece {
  Bishop(String team, Coordinate position){
    super(team, position);
  }

  public String getBoardName(){
    return "(" + this.getTeam().charAt(0) + ")" + " bishop";
  }
}

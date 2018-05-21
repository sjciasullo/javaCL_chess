package com.company;

public class Queen extends Piece {
  Queen(String team, Coordinate position){
    super(team, position);
  }

  public String getBoardName(){
    return "(" + this.getTeam().charAt(0) + ")" + " queen";
  }

  
}

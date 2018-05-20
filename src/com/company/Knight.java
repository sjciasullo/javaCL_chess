package com.company;

public class Knight extends Piece{
  Knight(String team, Coordinate position){
    super(team, position);
  }

  public String getBoardName(){
    return "(" + this.getTeam().charAt(0) + ")" + " knight";
  }

  public boolean isValidMove(String[][] board, Coordinate newPosition){
    String occupant = board[newPosition.getRow()][newPosition.getColumn()];
    int rowDifference = newPosition.getRow() - this.getPosition().getRow();
    int columnDifference = newPosition.getColumn() - this.getPosition().getColumn();

    if(occupant.isEmpty() || occupant.charAt(1) != this.getTeam().charAt(0)){
      if((Math.abs(columnDifference) == 2 && Math.abs(rowDifference) == 1) ||
          Math.abs(columnDifference) == 1 && Math.abs(rowDifference) == 2){
        return true;
      }
    }
    return false;
  }
}

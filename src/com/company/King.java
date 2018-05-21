package com.company;

public class King extends Piece{
  King(String team, Coordinate position){
    super(team, position);
  }

  public String getBoardName() {
    return "(" + this.getTeam().charAt(0) + ")" + " king";
  }

  public boolean isValidMove(String[][] board, Coordinate newPosition){
    String occupant = board[newPosition.getRow()][newPosition.getColumn()];
    int rowDifference = newPosition.getRow() - this.getPosition().getRow();
    int columnDifference = newPosition.getColumn() - this.getPosition().getColumn();

    if(occupant.isEmpty() || occupant.charAt(1) != this.getTeam().charAt(0)){
      if(Math.abs(columnDifference) <= 1 && Math.abs(rowDifference) <= 1){
        return true;
      }
    }
    return false;
  }
}

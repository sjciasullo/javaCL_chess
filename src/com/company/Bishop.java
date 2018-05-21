package com.company;

public class Bishop extends Piece {
  Bishop(String team, Coordinate position){
    super(team, position);
  }

  public String getBoardName(){
    return "(" + this.getTeam().charAt(0) + ")" + " bishop";
  }

  public boolean isValidMove(String[][] board, Coordinate newPosition) {
    String occupant = board[newPosition.getRow()][newPosition.getColumn()];
    if (occupant.isEmpty() || occupant.charAt(1) != this.getTeam().charAt(0)) {
      // first must check vertical line to prevent divide by zero
      if (newPosition.getColumn() == this.getPosition().getColumn()) {
        return false;
      }
      //calculate slope
      int changeY = newPosition.getRow() - this.getPosition().getRow();
      int changeX = newPosition.getColumn() - this.getPosition().getColumn();
      float slope = changeY / changeX;
      if (Math.abs(slope) == 1) {
        return Piece.isClearPath(board, this.getPosition(), newPosition);
      }
    }
    return false;
  }
}

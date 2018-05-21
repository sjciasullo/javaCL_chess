package com.company;

public class Queen extends Piece {
  Queen(String team, Coordinate position){
    super(team, position);
  }

  public String getBoardName(){
    return "(" + this.getTeam().charAt(0) + ")" + " queen";
  }

  public boolean isValidMove(String[][] board, Coordinate newPosition){
    String occupant = board[newPosition.getRow()][newPosition.getColumn()];
    if(occupant.isEmpty() || occupant.charAt(1) != this.getTeam().charAt(0)){
      if(this.getPosition().getColumn() == newPosition.getColumn()){
        return Piece.isClearPath(board, this.getPosition(), newPosition);
      }
      //calculate slope
      int changeY = newPosition.getRow() - this.getPosition().getRow();
      int changeX = newPosition.getColumn() - this.getPosition().getColumn();
      float slope = changeY / changeX;
      if(slope == 0 || Math.abs(slope) == 1){
        return Piece.isClearPath(board, this.getPosition(), newPosition);
      }
    }
    return false;
  }
}

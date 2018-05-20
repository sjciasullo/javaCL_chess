package com.company;

public class Pawn extends Piece{
  Pawn(String team, Coordinate position){
    super(team, position);
  }

  public String getBoardName(){
    return "(" + this.getTeam().charAt(0) + ")" + " pawn";
  }

  public boolean isValidMove(String[][] board, Coordinate newPosition){
    String occupant = board[ newPosition.getRow() ][ newPosition.getColumn() ];
    int teamFactor = this.getTeam().equals("black") ? 1 : -1;
    int newRow = newPosition.getRow();
    int newColumn = newPosition.getColumn();
    int currentRow = this.getPosition().getRow();
    int currentColumn = this.getPosition().getColumn();
    
    // Check if position is empty or held by other team. Ex. "(w) bishop" and black
    if(occupant.isEmpty()){
      // if same x value, check y value with account for team
      // black pawn increases rows, white pawn decreases rows
      if(newColumn == currentColumn){
        if(newRow == currentRow + teamFactor ||
           newRow == currentRow + (teamFactor * 2) && this.getFirstMove()){
          return true;
        }
      }
    } else if(occupant.charAt(1) != this.getTeam().charAt(0)){
      // needs to be diagonal and in correct direction
      if(newRow == currentRow + teamFactor &&
         (newColumn == currentColumn + 1 || newColumn == currentColumn - 1) ){
        return true;
      }
    }
    return false;
  }
}

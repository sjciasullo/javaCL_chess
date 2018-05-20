package com.company;

public class Rook extends Piece{
  Rook(String team, Coordinate position){
    super(team, position);
  }

  public String getBoardName(){
    return "(" + this.getTeam().charAt(0) + ")" + " rook";
  }

  public boolean isValidMove(String[][] board, Coordinate newPosition){
    int newRow = newPosition.getRow();
    int newColumn = newPosition.getColumn();
    int currentRow = this.getPosition().getRow();
    int currentColumn = this.getPosition().getColumn();
    String occupant = board[newRow][newColumn];

    if(occupant.isEmpty() || occupant.charAt(1) != this.getTeam().charAt(0)){
      if(currentRow == newRow){
        boolean obstacleDetected = false;
        int direction = newColumn < currentColumn ? -1 : 1;
        currentColumn += direction;
        // scan each spot in the line to see if there is an occupant
        while(currentColumn != newColumn){
          if(!board[currentRow][currentColumn].equals("")){
            return false;
          }
          currentColumn += direction;
        }
        return true;
      } else if(currentColumn == newColumn){
        boolean obstacleDetected = false;
        int direction = newRow < currentRow ? -1 : 1;
        currentRow += direction;
        // scan each spot in the line to see if there is an occupant
        while(currentRow != newRow){
          if(!board[currentRow][currentColumn].equals("")){
            return false;
          }
          currentRow += direction;
        }
        return true;
      }
    }
    return false;
  }
}

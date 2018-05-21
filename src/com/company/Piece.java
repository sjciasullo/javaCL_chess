package com.company;

abstract public class Piece {
  public static final int ROW = 0;
  public static final int COLUMN = 1;
  private boolean isFirstMove = true;
  private String team;
  private Coordinate position;

  Piece(String team, Coordinate position){
    this.team = team;
    this.position = new Coordinate(position.getRow(), position.getColumn());
  }

  public String getTeam(){
    return team;
  }

  public boolean getFirstMove(){
    return this.isFirstMove;
  }

  public Coordinate getPosition(){
    return position;
  }

  public void setPosition(Coordinate position){
    this.isFirstMove = false;
    this.position.setRow(position.getRow());
    this.position.setColumn(position.getColumn());
  }

  public boolean isValidMove(String[][] board, Coordinate newPosition){
    System.out.println("This should never execute as it should be overridden by all subclasses");
    return false;
  }

  public String getBoardName(){
    return "piece";
  }

  public static boolean isClearPath(String[][] board, Coordinate origin, Coordinate destination){
    int changeY = destination.getRow() - origin.getRow();
    int changeX = destination.getColumn() - origin.getColumn();
    int stepX, stepY;
    stepX = stepY = 1;

    // We know step-wise motion is 1 by 1 or horizontal because
    // we check slope before this method is ever used (queen, bishop)
    if(changeY < 0){
      stepY *= -1;
    }

    if(changeX < 0){
      stepX *= -1;
    }

    if(changeX == 0) {
      stepX = 0;
    }

    while(!origin.equals(destination)){
      origin.setRow(origin.getRow() + stepY);
      origin.setColumn(origin.getColumn() + stepX);
      if(!board[origin.getRow()][origin.getColumn()].equals("")){
        return false;
      }
    }
    return true;
  }
}

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
    float slope = (float) changeY / (float) changeX;

    //add checker for not drawing out of bounds
    if(Math.abs(slope) != 1 || slope != 0){
      return false;
    }

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

    origin.setRow(origin.getRow() + stepY);
    origin.setColumn(origin.getColumn() + stepX);
    while(!origin.equals(destination)){
      if(!board[origin.getRow()][origin.getColumn()].equals("")){
        return false;
      }
      origin.setRow(origin.getRow() + stepY);
      origin.setColumn(origin.getColumn() + stepX);
    }
    return true;
  }
}

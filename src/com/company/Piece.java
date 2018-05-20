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
}

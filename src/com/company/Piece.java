package com.company;

abstract public class Piece {
  public static final int ROW = 0;
  public static final int COLUMN = 1;
  private boolean isFirstMove = true;
  private String team;
  private Coordinate position;



  Piece(String team, Coordinate position){
    this.team = team;
    this.position.setRow(position.getRow());
    this.position.setColumn(position.getColumn());
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

  public void setPosition(int[] position){
    this.isFirstMove = false;
    this.position[0] = position[0];
    this.position[1] = position[1];
  }

  public String getBoardName(){
    return "piece";
  }
}

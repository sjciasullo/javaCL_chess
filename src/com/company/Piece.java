package com.company;

abstract public class Piece {
    public static final int ROW = 0;
    public static final int COLUMN = 1;
    private String team;
    private int[] position = new int[2];
    private boolean isFirstMove = true;


  Piece(String team, int[] position){
        this.team = team;
        this.position[0] = position[0];
        this.position[1] = position[1];
  }

  public String getTeam(){
    return team;
  }

  public int[] getPosition(){
    return position;
  }

  public boolean getFirstMove(){
    return this.isFirstMove;
  }

  public void setPosition(int[] position){
    this.isFirstMove = false;
    this.position[0] = position[0];
    this.position[1] = position[1];
  }
}

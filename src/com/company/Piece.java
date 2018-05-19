package com.company;

abstract public class Piece {
    public final int ROW = 0;
    public final int COLUMN = 1;
    private String team;
    private int[] position = new int[2];

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

    public void setPosition(int[] position){
        this.position[0] = position[0];
        this.position[1] = position[1];
    }
}

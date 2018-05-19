package com.company;

public class Pawn extends Piece{
    private boolean isFirstMove = true;

    Pawn(String team, int[] position){
        super(team, position);
    }

    public String getBoardName(){
        return "(" + this.getTeam().charAt(0) + ")" + " pawn";
    }
}

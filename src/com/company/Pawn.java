package com.company;

public class Pawn extends Piece{
    private boolean isFirstMove = true;

    Pawn(String team, int[] position){
        super(team, position);
    }

    public String getBoardName(){
        return "(" + this.getTeam().charAt(0) + ")" + " pawn";
    }

    public boolean canMoveToPosition(String[][] board, int[] newPosition){
        String occupant = board[ newPosition[0] ][ newPosition[1] ];

        // Check if position is empty or held by other team. Ex. "(w) bishop" and black
        if(occupant.isEmpty()){
            // if same x value, check y value with account for team
                // if difference is 1 then it is one away and unoccupied
        } else if(occupant.charAt(1) != this.getTeam().charAt(0)){
            // needs to be diagonal and in correct direction
        }
        return false;
    }
}

package com.company;

public class Coordinate {
  private int row;
  private int column;

  Coordinate(int row, int column){
    this.row = row;
    this.column = column;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  // Uses strategy from https://www.sitepoint.com/implement-javas-equals-method-correctly/
  @Override
  public boolean equals(Object obj){
    // self check for optimization
    if(this == obj){
      return true;
    }

    // null check and type check
    if (obj == null || !(obj instanceof Coordinate)){
      return false;
    }

    // content check
    return ((Coordinate) obj).getRow() == this.row &&
           ((Coordinate) obj).getColumn() == this.column;
  }

  @Override
  public int hashCode(){
    // specifically uses easy algorithm knowing bound of Coordinates used is [0,8)
    return this.column + (this.row * 8);
  }
}

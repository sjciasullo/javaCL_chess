package com.company;

public class BooleanWrapper {
  private boolean canUncheck = true;

  BooleanWrapper(){
  }

  public void setToFalse(){
    canUncheck = false;
  }

  public boolean getUncheck(){
    return canUncheck;
  }
}

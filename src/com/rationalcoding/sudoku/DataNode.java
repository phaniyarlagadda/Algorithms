package com.rationalcoding.sudoku;

public class DataNode extends Node{

   private String constraintName;
   private int constraintCount;
   
   public String getConstraintName() {
      return constraintName;
   }
   public void setConstraintName(String constraintName) {
      this.constraintName = constraintName;
   }
   public int getConstraintCount() {
      return constraintCount;
   }
   public void setConstraintCount(int constraintCount) {
      this.constraintCount = constraintCount;
   }
   
   public void decrementConstraintCount() {
       constraintCount--;
   }
   
   public void incrementConstraintCount() {
      constraintCount++;
  }
   
   @Override
   public String toString(){
      return "{name:"+constraintName+"}row:"+getRow()+"col:"+getCol()+"val:"+getVal();
   }

}

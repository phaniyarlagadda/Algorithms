package com.rationalcoding.sudoku;

public class Node {
   
   private int row;
   private int col;
   private int val;
   private ConstraintType constraintType;
   
   private Node leftNode;
   private Node rightNode;
   private Node topNode;
   private Node bottomNode;
   private DataNode columnHeader;
   
   public enum ConstraintType{
      CELL, ROW, COLUMN, BOX;
   }
   
   public Node(){
      
   }
   
   public Node(int row, int col, int val, ConstraintType constraintType){
      this.row = row;
      this.col = col;
      this.val = val;
      this.constraintType = constraintType;
   }
   
   public int getRow() {
      return row;
   }
   public void setRow(int row) {
      this.row = row;
   }
   public int getCol() {
      return col;
   }
   public void setCol(int col) {
      this.col = col;
   }
   public int getVal() {
      return val;
   }
   public void setVal(int val) {
      this.val = val;
   }
   public Node getLeftNode() {
      return leftNode;
   }
   public void setLeftNode(Node leftNode) {
      this.leftNode = leftNode;
   }
   public Node getRightNode() {
      return rightNode;
   }
   public void setRightNode(Node rightNode) {
      this.rightNode = rightNode;
   }
   public Node getTopNode() {
      return topNode;
   }
   public void setTopNode(Node topNode) {
      this.topNode = topNode;
   }
   public Node getBottomNode() {
      return bottomNode;
   }
   public void setBottomNode(Node bottomNode) {
      this.bottomNode = bottomNode;
   }

   public ConstraintType getConstraintType() {
      return constraintType;
   }

   public void setConstraintType(ConstraintType constraintType) {
      this.constraintType = constraintType;
   }
   
   @Override
   public boolean equals(Object other){
      if(other instanceof Node){
         Node o = (Node ) other;
         return ((o.getCol() == col) && (o.getRow() == row) && (o.getVal() == val) && (o.getConstraintType() == constraintType));
      }
      return false;
   }
   
   @Override
   public String toString(){
      return "{"+row+","+col+","+val+","+constraintType+"}";
   }

   public DataNode getColumnHeader() {
      return columnHeader;
   }

   public void setColumnHeader(DataNode columnHeader) {
      this.columnHeader = columnHeader;
   }

}

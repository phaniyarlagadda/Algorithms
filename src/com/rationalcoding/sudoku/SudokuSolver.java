package com.rationalcoding.sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Simple sudoku solvers 
 * 1. Simple recursive and brute force
 * 2. Fill in decreasing order of constraints
 * @author yarlagadda
 *
 */
public class SudokuSolver {
   private boolean finished = false;
   private List<Cell> cellChoices ;
   
   private class Cell implements Comparable<Cell>{
      private int row; 
      private int col;
      private List<Integer> possibleValues;
      
      public Cell(int row, int col){
         this.row = row;
         this.col = col;
         possibleValues = new ArrayList<Integer>();
      }
      
      public int getRow() {
         return row;
      }
      
      public int getCol() {
         return col;
      }
      
      @Override 
      public boolean equals(Object other){
         if(other instanceof Cell){
            Cell otherCell = (Cell )other;
            return (otherCell.getRow() == row) && (otherCell.getCol() == col);
         }
         return false;
      }

      public List<Integer> getPossibleValue() {
         return possibleValues;
      }

      public void addPossibleValue(int possibleValue) {
         possibleValues.add(possibleValue);
      }

      @Override
      public int compareTo(Cell o) {
         return ((possibleValues.size() > o.getPossibleValue().size())?1:possibleValues.size() == o.getPossibleValue().size() ? 0:-1);
      }
      
      @Override
      public String toString(){
         return "("+row+","+col+","+possibleValues.size()+")";
      }
   }
   
   public void solveSudoku(int[][] sudokuBoard) {
      cellChoices = new ArrayList<Cell>();
      System.out.println("Start printing input");
      printBoard(sudokuBoard);
      System.out.println("End printing input");
      buildCellChoices(sudokuBoard);
      Collections.sort(cellChoices);
      backtrack(sudokuBoard, 0);
   }
   
   public void solveSudokuSequential(int[][] sudokuBoard){
      System.out.println("Start printing input");
      printBoard(sudokuBoard);
      System.out.println("End printing input");
      backtrackSequential(sudokuBoard);
   }
   
   private void backtrack(int[][] sudokuBoard, int index) {
      if (isSolution(sudokuBoard)) {
         // process
         System.out.println("Solution found");
         printBoard(sudokuBoard);
         finished = true;
         return;
      }
      if(index >= cellChoices.size()){
         return;
      }
      Cell cell = cellChoices.get(index);
      for (int i:cell.getPossibleValue()) {
         if (isSafe(sudokuBoard, i, cell.getRow(), cell.getCol())) {
            sudokuBoard[cell.getRow()][cell.getCol()] = i;
            backtrack(sudokuBoard, index+1);
            if (finished) {
               return;
            }
            sudokuBoard[cell.getRow()][cell.getCol()] = 0;
         }
      }
      
   }
   
   private void backtrackSequential(int[][] sudokuBoard){
      if (isSolution(sudokuBoard)) {
         // process
         System.out.println("Solution found");
         printBoard(sudokuBoard);
         finished = true;
         return;
      }
      Cell unassigned = getUnAssignedCell(sudokuBoard);
      for (int i = 1; i <= sudokuBoard.length; i++) {
         if (isSafe(sudokuBoard, i, unassigned.getRow(), unassigned.getCol())) {
            sudokuBoard[unassigned.getRow()][unassigned.getCol()] = i;
            backtrackSequential(sudokuBoard);
            if (finished) {
               return;
            }
            sudokuBoard[unassigned.getRow()][unassigned.getCol()] = 0;
         }
      }
   }
   
   private void printBoard(int[][] sudokuBoard){
      for(int i=0;i< sudokuBoard.length;i++){
         for(int j=0 ; j< sudokuBoard.length; j++){
            System.out.print(sudokuBoard[i][j]+" ");
         }
         System.out.println();
      }
   }
   
   private void buildCellChoices(int[][] sudokuBoard){
      for(int row=0;row < sudokuBoard.length ; row++){
         for(int col= 0; col < sudokuBoard[0].length ; col++){
            if(sudokuBoard[row][col] == 0){
               Cell constrainedcell = new Cell(row, col);
               for(int i=1; i<= sudokuBoard.length; i++){
                  if(isSafe(sudokuBoard, i, row, col)){
                     constrainedcell.addPossibleValue(i);
                  }
               }
               cellChoices.add(constrainedcell);
            }
         }
      }
   }
   
   private boolean isSolution(int[][] sudokuBoard){
      for(int row=0;row < sudokuBoard.length ; row++){
         for(int col= 0; col < sudokuBoard[0].length ; col++){
            if(sudokuBoard[row][col] == 0){
               return false;
            }
         }
      }
      return true;
   }
   
   private Cell getUnAssignedCell(int[][] sudokuBoard){
      for(int row=0;row < sudokuBoard.length ; row++){
         for(int col= 0; col < sudokuBoard[0].length ; col++){
            if(sudokuBoard[row][col] == 0){
               Cell unassigned = new Cell(row, col);
               return unassigned;
            }
         }
      }
      return null;
   }
   
   private boolean isSafe(int[][] sudokuBoard, int val, int row, int col) {
      return (isGridSafe(sudokuBoard, val, row, col)) && (isRowSafe(sudokuBoard, val, row, col))
            && (isColumnSafe(sudokuBoard, val, row, col));
   }
   
   private boolean isGridSafe(int[][] sudokuBoard, int val, int row, int col){
      row = row-(row%3);
      col = col-(col%3);
      for(int i=0; i < 3; i++){
         for(int j=0; j<3; j++){
            if(sudokuBoard[i+row][j+col] == val){
               return false;
            }
         }
      }
      return true;
   }
   
   private boolean isRowSafe(int[][] sudokuBoard, int val, int row, int col){
      for(int index = 0; index < sudokuBoard.length; index++){
         if(sudokuBoard[row][index] == val){
            return false;
         }
      }
      return true;
   }
   
   private boolean isColumnSafe(int[][] sudokuBoard, int val, int row, int col){
      for(int index = 0; index < sudokuBoard.length; index++){
         if(sudokuBoard[index][col] == val){
            return false;
         }
      }
      return true;
   }

}

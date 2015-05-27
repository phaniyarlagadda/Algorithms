package com.rationalcoding.sudoku.test;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.rationalcoding.sudoku.SudokuSolverDLX;


/**
 * Sudoku solver basic tests
 * @author yarlagadda
 *
 */
public class SudokuSolverDLXTest {
   
   @Test
   public void basicTest(){
      int[][] sudokuBoard = {
            {3,0, 6, 5, 0, 8, 4, 0, 0},
            {5,2, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 7, 0, 0, 0, 0, 3, 1 },
            {0, 0, 3, 0, 1, 0, 0, 8, 0 },
            {9, 0, 0, 8, 6, 3, 0, 0, 5},
            {0, 5, 0, 0, 9, 0, 6, 0, 0 },
            {1, 3, 0, 0, 0, 0, 2, 5, 0 },
            {0, 0, 0, 0, 0, 0, 0, 7, 4},
            {0, 0, 5, 2, 0, 6, 3, 0, 0 },
            
      };
      solveAndPrint(sudokuBoard);
   }
   
   private void solveAndPrint(int[][] sudokuBoard){
      int[][] temp = deepCopy(sudokuBoard);
      long start = System.currentTimeMillis();
      SudokuSolverDLX dancingLinks = new SudokuSolverDLX(temp.length);
      dancingLinks.solve(temp);
      long end = System.currentTimeMillis();
      System.out.println("Time to solve:"+(end-start)+" milliseconds");
   }
   
   private int[][] deepCopy(int[][] original){
      int[][] temp = new int[original.length][];
      for (int i = 0; i < original.length; i++) {
         temp[i] = Arrays.copyOf(original[i], original[i].length);
     }
      return temp;
   }
   
   @Test
   public void basictest1(){
      int[][] sudokuBoard = {
            {0,0,0,7,0,2,0,6,0},
            {0,6,8,0,0,0,2,1,5},
            {0,0,2,0,0,5,0,0,0},
            {0,0,6,2,0,3,0,5,0},
            {8,0,3,0,0,0,9,0,1},
            {0,1,0,4,0,8,6,0,0},
            {0,0,0,8,0,0,5,0,0},
            {2,8,9,0,0,0,7,3,0},
            {0,3,0,9,0,7,0,0,0},
            
      };
      solveAndPrint(sudokuBoard);
   }
   
   @Test
   public void hardSudokuTest(){
      int[][] sudokuBoard = {
            {3,0,4,1,0,0,0,0,0},
            {0,7,0,0,9,0,8,4,6},
            {0,0,0,4,0,0,0,5,0},
            {7,4,0,0,0,0,0,0,0},
            {5,0,0,0,8,0,0,0,9},
            {0,0,0,0,0,0,0,3,2},
            {0,3,0,0,0,9,0,0,0},
            {2,8,9,0,1,0,0,7,0},
            {0,0,0,0,0,3,9,0,1},
            
      };
      solveAndPrint(sudokuBoard);
   }
   
   @Test
   public void smallSudoku(){
      int[][] sudokuBoard = {
            {3,4,1,0},
            {0,2,0,0},
            {0,0,2,0},
            {0,1,4,3}            
      };
      solveAndPrint(sudokuBoard);
   }

}

package com.rationalcoding.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.rationalcoding.sudoku.Node.ConstraintType;



/**
 * Sudoku solver using the dancing links technique by Knuth
 * Ref: http://en.wikipedia.org/wiki/Exact_cover
 * http://www.stolaf.edu/people/hansonr/sudoku/exactcovermatrix.htm
 * http://en.wikipedia.org/wiki/Knuth's_Algorithm_X
 * @author yarlagadda
 *
 */
public class SudokuSolverDLX {
   
   private DataNode head;
   private int size;
   private int numberOfRows;
   private Stack<Node> solution ;
   private List<Node> headerNodes;
   private List<DataNode> dataNodes;
   
   public SudokuSolverDLX(int size){
      this.size = size;
      numberOfRows = size*size*size;
   }
   
   /**
    * initializes and connects the required rows and columns
    */
   public void intialize(){
      List<Node> cellConstraints = new ArrayList<Node>();
      List<Node> rowConstraints = new ArrayList<Node>();
      List<Node> colConstraints = new ArrayList<Node>();
      List<Node> boxConstraints = new ArrayList<Node>();
      solution = new Stack<Node>();
      headerNodes = new ArrayList<Node>();
      for(int i=0; i< numberOfRows; i++){
         int val = i%size;
         int chunk = i/size;
         int row = chunk/size;
         int col = chunk%size;
         // set the right columns
         Node cellConstraintNode = new Node(row, col, val, ConstraintType.CELL);
         Node rowConstraintNode = new Node(row, col, val, ConstraintType.ROW);
         Node columnConstraintNode = new Node(row, col, val, ConstraintType.COLUMN);
         Node boxConstraintNode = new Node(row, col, val, ConstraintType.BOX);
         cellConstraintNode.setRightNode(rowConstraintNode);
         cellConstraintNode.setLeftNode(boxConstraintNode);
         rowConstraintNode.setRightNode(columnConstraintNode);
         rowConstraintNode.setLeftNode(cellConstraintNode);
         columnConstraintNode.setRightNode(boxConstraintNode);
         columnConstraintNode.setLeftNode(rowConstraintNode);
         boxConstraintNode.setRightNode(cellConstraintNode);
         boxConstraintNode.setLeftNode(columnConstraintNode);
         
         cellConstraints.add(cellConstraintNode);
         rowConstraints.add(rowConstraintNode);
         colConstraints.add(columnConstraintNode);
         boxConstraints.add(boxConstraintNode);
      }
      
      // connect the top and bottom for cell constraint
      connectCellConstraints(cellConstraints, headerNodes);
      
      // connect the top and bottom for row constraint
      connectRowConstraints(rowConstraints, headerNodes);
      
      // connect the top and bottom for col constraints
      connectColConstraints(colConstraints, headerNodes);
      
      // connect box constraints
      connectBoxConstraints(boxConstraints, headerNodes);
      
      // connect the header columns
      connectTopConstraints(headerNodes);
            
   }
   
   // TODO : remove debug code
   /*private void print(){
      System.out.println("in printing");
      for(Node d = head.getRightNode(); d!= head ;  d = d.getRightNode()){
         //System.out.println((((DataNode) d).getConstraintName())+" is constraint name");
         System.out.println("new constraint");
         for(Node bottom = d.getBottomNode(); bottom != d; bottom = bottom.getBottomNode()){
            for(Node right = bottom.getRightNode(); right != bottom; right = right.getRightNode())
               System.out.print(right+"->");
            System.out.println();
         }
         
      }
      System.out.println("end printing");
   }
   
   private void printConstraints(){
      System.out.println("in printing");
      for(Node d = head.getRightNode(); d!= head ;  d = d.getRightNode()){
         System.out.println("new constraint"+d);
      }
   }*/
   
   private int getConstraintCount(Node head){
      int count = 1;
      for(Node current = head.getBottomNode(); current != head; current = current.getBottomNode()){
         count++;
      }
      return count;
   }
   
   private void connectTopConstraints(List<Node> topConstraints){
      //System.out.println("Connecting data nodes for columns");
      dataNodes = new ArrayList<DataNode>();
      for(int i=0; i< topConstraints.size();i++){
         DataNode dataNode = new DataNode();
         Node current = topConstraints.get(i);
         setColumnHeader(dataNode, current);
         dataNode.setColumnHeader(dataNode);
         dataNode.setRow(current.getRow());
         dataNode.setCol(current.getCol());
         dataNode.setVal(current.getVal());
         dataNode.setBottomNode(current);
         dataNode.setTopNode(current.getTopNode());
         current.getTopNode().setBottomNode(dataNode);
         current.setTopNode(dataNode);
         // init count and constraint name
         dataNode.setConstraintCount(getConstraintCount(current));
         dataNode.setConstraintName(current.toString());
         dataNodes.add(dataNode);
      }
      
      for(int i=0; i< dataNodes.size(); i++){
         DataNode current = dataNodes.get(i);
         if(i == 0){
            current.setLeftNode(dataNodes.get(dataNodes.size()-1));
            current.setRightNode(dataNodes.get(i+1));
         }else if(i == dataNodes.size()-1){
            current.setRightNode(dataNodes.get(0));
            current.setLeftNode(dataNodes.get(i-1));
         }else{
            current.setRightNode(dataNodes.get(i+1));
            current.setLeftNode(dataNodes.get(i-1));
         }
      }
      head = new DataNode();
      head.setRightNode(dataNodes.get(0));
      head.setLeftNode(dataNodes.get(0).getLeftNode());
      dataNodes.get(0).getLeftNode().setRightNode(head);
      dataNodes.get(0).setLeftNode(head);      
   }
   
   private void setColumnHeader(DataNode header, Node top){
      top.setColumnHeader(header);
      for(Node t = top.getBottomNode(); t!= top; t=t.getBottomNode()){
         t.setColumnHeader(header);
      }
   }
   
   private void connectCellConstraints(List<Node> nodes, List<Node> topConstraints){
      //System.out.println("Connecting cell constraints");
      for(int i=0; i< nodes.size(); i++){
         int rowIndex = nodes.get(i).getRow();
         int colIndex = nodes.get(i).getCol();
         int valIndex = nodes.get(i).getVal();
         int bottomVal = valIndex+1;
         int topVal = valIndex-1;
         if((valIndex+1)% size == 0){
            bottomVal = bottomVal-size; 
         }else if(valIndex%size == 0){
            topVal = topVal+size;
            topConstraints.add(nodes.get(i));
         }
         int bottomIndex = rowIndex*size*size+(colIndex)*size+bottomVal;
         int topIndex = rowIndex*size*size+colIndex*size+topVal;
         nodes.get(i).setBottomNode(nodes.get(bottomIndex));
         nodes.get(i).setTopNode(nodes.get(topIndex));
      }
   }
   
   private void connectRowConstraints(List<Node> nodes, List<Node> topConstraints){
      //System.out.println("Connecting row constraints");
      for(int i=0; i< nodes.size(); i++){
         int rowIndex = nodes.get(i).getRow();
         int colIndex = nodes.get(i).getCol();
         int bottomColIndex = colIndex+1;
         int topColIndex = colIndex-1;
         if(colIndex % size == 0){
            topConstraints.add(nodes.get(i));
            topColIndex = colIndex+size-1;
         }else if((colIndex+1) % (size) == 0){
            bottomColIndex = colIndex-size+1;
         }
         int bottomIndex = rowIndex*size*size+(bottomColIndex)*size+nodes.get(i).getVal();
         int topIndex = rowIndex*size*size+topColIndex*size+nodes.get(i).getVal();
         nodes.get(i).setBottomNode(nodes.get(bottomIndex));
         nodes.get(i).setTopNode(nodes.get(topIndex));
      }
   }
   
   private void connectColConstraints(List<Node> nodes, List<Node> topConstraints){
      //System.out.println("Connecting column constraints");
      for(int i=0; i< nodes.size(); i++){
         int rowIndex = nodes.get(i).getRow();
         int colIndex = nodes.get(i).getCol();
         int bottomRowIndex = rowIndex+1;
         int topRowIndex = rowIndex-1;
         if(rowIndex % size == 0){
            topConstraints.add(nodes.get(i));
            topRowIndex = rowIndex+size-1;
         }else if((rowIndex+1) % (size) == 0){
            bottomRowIndex = rowIndex-size+1;
         }
         int bottomIndex = colIndex*size+(bottomRowIndex)*size*size+nodes.get(i).getVal();
         int topIndex = colIndex*size+topRowIndex*size*size+nodes.get(i).getVal();
         nodes.get(i).setBottomNode(nodes.get(bottomIndex));
         nodes.get(i).setTopNode(nodes.get(topIndex));
      }
   }
   
   private void connectBoxConstraints(List<Node> nodes, List<Node> topConstraints){
      //System.out.println("Connecting box constraints");
      int boxSize = (int )Math.sqrt(size);
      for(int i=0; i< nodes.size(); i++){
         int rowIndex = nodes.get(i).getRow();
         int colIndex = nodes.get(i).getCol();
         int bottomRowIndex = rowIndex;
         int bottomColIndex = colIndex+1;
         int topRowIndex = rowIndex;
         int topColIndex = colIndex-1;
         int startRow = rowIndex-(rowIndex%boxSize);
         int startCol = colIndex-(colIndex%boxSize);
         // if its starting element
         if(startCol == colIndex && startRow == rowIndex){
            topConstraints.add(nodes.get(i));
            topRowIndex = startRow+boxSize-1;
            topColIndex = startCol+boxSize-1;
         }else if(colIndex == (startCol+boxSize-1) && rowIndex != (startRow+boxSize-1)){
            bottomRowIndex = rowIndex+1;
            bottomColIndex = startCol;
         }else if(colIndex == startCol){
            topRowIndex = rowIndex-1;
            topColIndex = startCol+boxSize-1;
         }else if(rowIndex == (startRow+boxSize-1) && (colIndex == (startCol+boxSize-1))){
            bottomColIndex = startCol;
            bottomRowIndex = startRow;
         }

         int bottomIndex = bottomColIndex*size+(bottomRowIndex)*size*size+nodes.get(i).getVal();
         int topIndex = topColIndex*size+topRowIndex*size*size+nodes.get(i).getVal();
         nodes.get(i).setBottomNode(nodes.get(bottomIndex));
         nodes.get(i).setTopNode(nodes.get(topIndex));
      }
   }
   
   /**
    *  util method to cover the constraint
    * @param column
    */
   private void cover(Node column) {
      //System.out.println("Covering :"+column);
      column.getRightNode().setLeftNode(column.getLeftNode());
      column.getLeftNode().setRightNode(column.getRightNode());
      for (Node row = column.getBottomNode(); row != column; row = row.getBottomNode()) {
         for (Node rightNode = row.getRightNode(); rightNode != row; rightNode = rightNode
               .getRightNode()) {
            rightNode.getTopNode().setBottomNode(rightNode.getBottomNode());
            rightNode.getBottomNode().setTopNode(rightNode.getTopNode());
            rightNode.getColumnHeader().decrementConstraintCount();
         }
      }
   }
   
   /**
    *  util method to uncover the constraint
    * @param column
    */
   private void uncover(Node column) {
      for (Node row = column.getTopNode(); row != column; row = row.getTopNode()) {
         for (Node leftNode = row.getLeftNode(); leftNode != row; leftNode = leftNode.getLeftNode()) {
            leftNode.getTopNode().setBottomNode(leftNode);
            leftNode.getBottomNode().setTopNode(leftNode);
            leftNode.getColumnHeader().incrementConstraintCount();
         }
      }
      column.getRightNode().setLeftNode(column);
      column.getLeftNode().setRightNode(column);
   }
   
   /**
    *  recursive solve method
    */
   private void solve() {

      // if head points to itself then we have a solution
      if (head.getRightNode() == head) {
         printSolution();
         return;
      }
      Node column = chooseNextColumn();
      cover(column);
      for (Node row = column.getBottomNode(); row != column; row = row.getBottomNode()) {
         solution.add(row);
         for (Node rightNode = row.getRightNode(); rightNode != row; rightNode = rightNode
               .getRightNode()) {
            cover(rightNode.getColumnHeader());
         }

         solve();
         solution.remove(row);

         for (Node leftNode = row.getLeftNode(); leftNode != row; leftNode =
               leftNode.getLeftNode()) {
            uncover(leftNode.getColumnHeader());
         }
      }
      uncover(column);
   }
   
   /**
    * util to print the solution
    */
   private void printSolution() {
      
      int[][] solutionMatrix = new int[size][size];
      for(Node n:solution){
         solutionMatrix[n.getRow()][n.getCol()] = n.getVal()+1;
      }
      System.out.println("printing solution");
      for(int[] row:solutionMatrix){
         System.out.println(StringUtils.join(ArrayUtils.toObject(row), " "));
      }
      System.out.println("end printing solution");
      
   }
   
   /**
    * util to remove the values from input
    * @param sudkuBoard
    */
   private void removeGivenNodes(int[][] sudkuBoard){
      for(int i=0; i< sudkuBoard.length; i++){
         for(int j=0; j< sudkuBoard.length; j++){
            if(sudkuBoard[i][j] != 0){
               Node req = findNode(i,j, sudkuBoard[i][j]);
               if(req == null){
                  throw new IllegalArgumentException("Illegal sudoku board");
               }
               solution.add(req);
               cover(req.getColumnHeader());
               Node temp = req.getRightNode();
               while(temp != req){
                  cover(temp.getColumnHeader());
                  temp = temp.getRightNode();
               }
            }
         }
      }
   }
   
   private Node findNode(int rowIndex, int colIndex, int val) {

      Node column = head.getRightNode();
      for (; column != head; column = column.getRightNode()) {
         for (Node row = column.getBottomNode(); row != column; row = row.getBottomNode()) {
            if (row.getRow() == rowIndex && row.getCol() == colIndex && (row.getVal() + 1) == val) {
               return row;
            }
         }

      }
      return null;
   }
   
   /**
    * Util to choose next constraint column.
    * Chooses with minimum constraints
    * @return
    */
   private Node chooseNextColumn(){
      int min = Integer.MAX_VALUE;
      Node minNode = null;
      for(Node temp = head.getRightNode();temp != head; temp = temp.getRightNode()){
         if(temp.getColumnHeader().getConstraintCount() < min){
            min = temp.getColumnHeader().getConstraintCount();
            minNode = temp;
         }
      }
      return minNode;
   }
   
   /**
    * Public method to solve sudoku board
    * @param sudokuBoard
    */
   public void solve(int[][] sudokuBoard){
      intialize();
      removeGivenNodes(sudokuBoard);
      solve();
   }

}

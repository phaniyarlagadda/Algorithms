package com.rationalcoding.combinatorics;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic class which generates combinations from list of lists
 * The combinations are generated recursively
 * @author yarlagadda
 *
 * @param <T>
 */
public class CombinationsOfListsRecursive<T>{
   
   private List<ArrayList<T>> output ;
   
   public List<ArrayList<T>> getCombinationOfLists(List<ArrayList<T>> input){
      output = new ArrayList<ArrayList<T>>();
      // store just the index instead of objects
      int[] partialIndex = new int[input.size()];
      backtrack(partialIndex, 0, input);
      return output;
   }
   
   private void backtrack(int[] partialIndex, int k, List<ArrayList<T>> input){
      if(isSolution(partialIndex, k, input)){
         processSolution(partialIndex, k, input);
         return;
      }
      
      // for kth list all elements are possible candidates
      for(int i=0;i<input.get(k).size();i++){
         partialIndex[k] = i;
         // backtrack with remaining options
         backtrack(partialIndex, k+1, input);
      }
   }
   
   private boolean isSolution(int[] partialIndex, int k, List<ArrayList<T>> input){
      return (k == input.size());
   }
   
   private void processSolution(int[] partialIndex, int k, List<ArrayList<T>> input){
      ArrayList<T> combination = new ArrayList<T>();
      for(int index=0; index < partialIndex.length; index++){
         combination.add(input.get(index).get(partialIndex[index]));
      }
      output.add(combination);
   }

}

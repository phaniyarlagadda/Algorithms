package com.rationalcoding.combinatorics;

import java.util.ArrayList;
import java.util.List;

/**
 * Recursive approach to generate all subsets.
 * For each element in input it can either be part of a subset or not. The possibilities are enumerated and output solution vector is generated.
 * Ref : The Algorithm Design by Steven Skienna
 * @author yarlagadda
 *
 * @param <T>
 */
public class GenerateAllSubSets<T> {

   private List<ArrayList<T>> subsets ;
   
   public List<ArrayList<T>> generateAllSubSets(T[] input){
      subsets = new ArrayList<ArrayList<T>>();
      boolean[] partial = new boolean[input.length];
      backtrack(input, 0, partial);
      return subsets;
   }
   
   private void backtrack(T[] input, int k, boolean[] partial){
      
      // check if partial is the solution
      // process if true
      if(isSolution(input, k, partial)){
         // process
         processSolution(input, k, partial);
         return;
      }
      // 2 possibilities for each element.
      // either it is present in the subset or it is not present in subset
      partial[k] = false;
      // recursively call for remaining elements
      backtrack(input, k+1, partial);
      partial[k] = true;
      backtrack(input, k+1, partial);
   }
   
   private boolean isSolution(T[] input, int k , boolean[] partial){
      return (k == input.length);
   }
   
   private void processSolution(T[] input, int k , boolean[] partial){
      // construct the output based on the partial
      // if an element is true then it means the element in input at that index is part of the subset
      ArrayList<T> currentSubSet = new ArrayList<T>();
      for(int index=0; index < partial.length; index++){
         if(partial[index]){
            currentSubSet.add(input[index]);
         }
      }
      subsets.add(currentSubSet);
   }   

}

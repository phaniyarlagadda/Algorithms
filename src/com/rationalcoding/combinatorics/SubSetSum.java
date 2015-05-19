package com.rationalcoding.combinatorics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Util class to generate combinations
 * 
 */
public class SubSetSum {

private List<ArrayList<Integer>> subsets ;
   
   public List<ArrayList<Integer>> findSubsetsWithTargetSum(int[] input, int targetSum){
      Arrays.sort(input);
      subsets = new ArrayList<ArrayList<Integer>>();
      int currentSum = 0;
      boolean[] partial = new boolean[input.length];
      backtrack(input, targetSum, 0, partial, currentSum);
      return subsets;
   }
   
   private void backtrack(int[] input, int targetSum, int k, boolean[] partial, int currentSum){
      
      // check if partial is the solution
      // process if true
      if(isSolution(input, k, partial) ){
         // process only if target sum and current sum are equal
         if(targetSum == currentSum){
            processSolution(input, targetSum, k, partial, currentSum);
         }
         return;
      }else if(currentSum > targetSum){
         // return since array is sorted other elements wont fit
         return;
      }
      // 2 possibilities for each element.
      // either it is present in the subset or it is not present in subset
      partial[k] = false;
      // recursively call for remaining elements
      backtrack(input, targetSum, k+1, partial, currentSum);
      partial[k] = true;
      // update the current sum since element is part of subset
      backtrack(input, targetSum, k+1, partial, currentSum+input[k]);
   }
   
   private boolean isSolution(int[] input, int k , boolean[] partial){
      return (k == input.length);
   }
   
   private void processSolution(int[] input, int targetSum, int k , boolean[] partial, int currentSum){
      // add to required if sum equals target sum
      // construct the output based on the partial
      // if an element is true then it means the element in input at that index is part of the subset
      ArrayList<Integer> currentSubSet = new ArrayList<Integer>();
      for(int index=0; index < partial.length; index++){
         if(partial[index]){
            currentSubSet.add(input[index]);
         }
      }
      subsets.add(currentSubSet);
   }   
}

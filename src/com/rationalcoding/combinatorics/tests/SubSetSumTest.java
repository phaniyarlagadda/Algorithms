package com.rationalcoding.combinatorics.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.rationalcoding.combinatorics.SubSetSum;

/**
 * Test cases to verify combinations which sum to a target sum 
 * 
 *
 */
public class SubSetSumTest {
  
	SubSetSum subSetSumUtil = new SubSetSum();
	
  @Test
  public void testOneCombinationPresent() {
	  int[] input = { 6,4,7,5};
	  int targetSum = 9;
	  List<ArrayList<Integer>>  combinations = subSetSumUtil.findSubsetsWithTargetSum(input, targetSum);
	  printCombinations(combinations,targetSum, input);
	  	  
  }
  
  @Test
  public void testMultipleCombinationsPresent() {
	  int[] input = { 1,2,3,6,7,8};
	  int targetSum = 10;
	  List<ArrayList<Integer>>  combinations = subSetSumUtil.findSubsetsWithTargetSum(input, targetSum);
	  printCombinations(combinations,targetSum, input);
	  	  
  }
  
  @Test
  public void testSingleElement() {
	  int[] input = { 8,4,5,2,3};
	  int targetSum = 2;
	  List<ArrayList<Integer>>  combinations = subSetSumUtil.findSubsetsWithTargetSum(input, targetSum);
	  printCombinations(combinations,targetSum, input);
	  	  
  }
  
  @Test
  public void testAllElementPresentCombination() {
	  int[] input = { 4,5,6,7};
	  int targetSum = 22;
	  List<ArrayList<Integer>>  combinations = subSetSumUtil.findSubsetsWithTargetSum(input, targetSum);
	  printCombinations(combinations,targetSum, input);
	  	  
  }
  
  @Test
  public void testCombinationNotPresentSumWithinRange() {
	  int[] input = { 3,7,9,10};
	  int targetSum = 8;
	  List<ArrayList<Integer>>  combinations = subSetSumUtil.findSubsetsWithTargetSum(input, targetSum);
	  printCombinations(combinations,targetSum, input);
	  	  
  }
  
  @Test
  public void testCombinationNotPresentSumIsGreater() {
	  int[] input = { 8,4,5,2,10};
	  int targetSum = 25;
	  List<ArrayList<Integer>>  combinations = subSetSumUtil.findSubsetsWithTargetSum(input, targetSum);
	  printCombinations(combinations,targetSum, input);
	  	  
  }
  
  @Test
  public void testLargeArray() {
	  int[] input = { 5,8,5,10,12,19,13,22,24,2};
	  int targetSum = 18;
	  List<ArrayList<Integer>>  combinations = subSetSumUtil.findSubsetsWithTargetSum(input, targetSum);
	  printCombinations(combinations,targetSum, input);
	  	  
  }
  
  @Test
  public void testSmallInput(){
	  int[] input = { 1};
	  int targetSum = 18;
	  List<ArrayList<Integer>>  combinations = subSetSumUtil.findSubsetsWithTargetSum(input, targetSum);
	  printCombinations(combinations,targetSum, input);
  }
  
  @Test
  public void testEmptyArray(){
	  int[] input = { };
	  int targetSum = 1;
	  List<ArrayList<Integer>>  combinations = subSetSumUtil.findSubsetsWithTargetSum(input, targetSum);
	  printCombinations(combinations,targetSum, input);
  }
  
  @Test
  public void testEmptyArrayZeroSum(){
     int[] input = { };
     int targetSum = 0;
     List<ArrayList<Integer>>  combinations = subSetSumUtil.findSubsetsWithTargetSum(input, targetSum);
     printCombinations(combinations,targetSum, input);
  }
  
  @Test
  public void testTargetSumIsZero(){
	  int[] input = { 4,5,6,7};
	  int targetSum = 0;
	  List<ArrayList<Integer>>  combinations = subSetSumUtil.findSubsetsWithTargetSum(input, targetSum);
	  printCombinations(combinations,targetSum, input);
  }
  
  private void printCombinations(List<ArrayList<Integer>> combinations, int targetSum,int[] input){
	  System.out.println("Printing combinations for input "+Arrays.toString(input)+" and for target sum :"+targetSum);
	  System.out.println("Number of combinations found : "+combinations.size());
	  for(ArrayList<Integer> combinationFound:combinations){
		  int currentCombinationSum = 0;
		  for(int index = 0;index <combinationFound.size();index++){
			  currentCombinationSum += combinationFound.get(index);
		  }
		  // assert sum to make sure its a valid combination
		  Assert.assertEquals(targetSum, currentCombinationSum);
		  System.out.println(StringUtils.join(combinationFound, ","));
	  }
	  System.out.println("End printing combinations");
  }
}


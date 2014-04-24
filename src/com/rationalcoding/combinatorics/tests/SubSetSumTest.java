package com.rationalcoding.combinatorics.tests;

import java.util.ArrayList;
import java.util.Arrays;

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
	  int[] input = { 4,5,6,7};
	  int targetSum = 9;
	  ArrayList<ArrayList<Integer>>  combinations = subSetSumUtil.getCombinations(input, targetSum);
	  printCombinations(combinations,targetSum, input);
	  	  
  }
  
  @Test
  public void testOneCombinationPresentMiddleElements() {
	  int[] input = { 4,5,6,9};
	  int targetSum = 11;
	  ArrayList<ArrayList<Integer>>  combinations = subSetSumUtil.getCombinations(input, targetSum);
	  printCombinations(combinations,targetSum, input);
	  	  
  }
  
  @Test
  public void testMultipleCombinationsPresent() {
	  int[] input = { 1,2,3,6,7,8};
	  int targetSum = 10;
	  ArrayList<ArrayList<Integer>>  combinations = subSetSumUtil.getCombinations(input, targetSum);
	  printCombinations(combinations,targetSum, input);
	  	  
  }
  
  @Test
  public void testSingleElement() {
	  int[] input = { 1,2,3,6,7,8};
	  int targetSum = 2;
	  ArrayList<ArrayList<Integer>>  combinations = subSetSumUtil.getCombinations(input, targetSum);
	  printCombinations(combinations,targetSum, input);
	  	  
  }
  
  @Test
  public void testAllElementPresentCombination() {
	  int[] input = { 4,5,6,7};
	  int targetSum = 22;
	  ArrayList<ArrayList<Integer>>  combinations = subSetSumUtil.getCombinations(input, targetSum);
	  printCombinations(combinations,targetSum, input);
	  	  
  }
  
  @Test
  public void testCombinationNotPresentSumWithinRange() {
	  int[] input = { 4,5,6,7};
	  int targetSum = 8;
	  ArrayList<ArrayList<Integer>>  combinations = subSetSumUtil.getCombinations(input, targetSum);
	  printCombinations(combinations,targetSum, input);
	  	  
  }
  
  @Test
  public void testCombinationNotPresentSumIsGreater() {
	  int[] input = { 4,5,6,7};
	  int targetSum = 25;
	  ArrayList<ArrayList<Integer>>  combinations = subSetSumUtil.getCombinations(input, targetSum);
	  printCombinations(combinations,targetSum, input);
	  	  
  }
  
  @Test
  public void testLargeArray() {
	  int[] input = { 1,2,3,4,5,6,7,8,9};
	  int targetSum = 18;
	  ArrayList<ArrayList<Integer>>  combinations = subSetSumUtil.getCombinations(input, targetSum);
	  printCombinations(combinations,targetSum, input);
	  	  
  }
  
  @Test
  public void testSmallInput(){
	  int[] input = { 1};
	  int targetSum = 18;
	  ArrayList<ArrayList<Integer>>  combinations = subSetSumUtil.getCombinations(input, targetSum);
	  printCombinations(combinations,targetSum, input);
  }
  
  @Test
  public void testEmptyArray(){
	  int[] input = { };
	  int targetSum = 1;
	  ArrayList<ArrayList<Integer>>  combinations = subSetSumUtil.getCombinations(input, targetSum);
	  printCombinations(combinations,targetSum, input);
  }
  
  @Test
  public void testTargetSumIsZero(){
	  int[] input = { 4,5,6,7};
	  int targetSum = 0;
	  ArrayList<ArrayList<Integer>>  combinations = subSetSumUtil.getCombinations(input, targetSum);
	  printCombinations(combinations,targetSum, input);
  }
  
  private void printCombinations(ArrayList<ArrayList<Integer>> combinations, int targetSum,int[] input){
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


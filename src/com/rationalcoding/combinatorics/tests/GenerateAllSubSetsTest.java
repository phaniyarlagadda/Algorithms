package com.rationalcoding.combinatorics.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rationalcoding.combinatorics.GenerateAllSubSets;
import com.rationalcoding.combinatorics.GenerateAllSubSetsIterative;

/**
 * Basic tests to verify generate subset functionality works. Both iterative and recursive approach are tested.
 * The tests only verify the output size. Generated output has to be verified manually. 
 * @author yarlagadda
 *
 */
public class GenerateAllSubSetsTest {
   
   private GenerateAllSubSets<Integer> generateSubSetsRecursiveUtil;
   private GenerateAllSubSetsIterative<Integer> generateSubSetsIterativeUtil;
   
   @BeforeMethod
   public void setup(){
      generateSubSetsRecursiveUtil = new GenerateAllSubSets<Integer>();
      
   }

   @Test
   public void testInputWithTwoElements(){
      Integer[] input = {1,2};
      verifyOutput(input);
   }
   
   @Test
   public void testInputWithOneElement(){
      Integer[] input = {1};
      verifyOutput(input);
   }
   
   @Test
   public void testInputWithEmptyInput(){
      Integer[] input = {};
      verifyOutput(input);
   }
   
   @Test
   public void testInputWithLargeInput(){
      Integer[] input = {1,2,3,4,5,6,7,8,9};
      verifyOutput(input);
   }
   
   private void verifyOutput(Integer[] input){
      // verify size of the output is as expected
      List<ArrayList<Integer>> recursiveOutput = generateSubSetsRecursiveUtil.generateAllSubSets(input);
      Assert.assertEquals(recursiveOutput.size(), (int )Math.pow(2, input.length), "Subset count did not match for recursive solution");
      System.out.println("Printing solution vector for recursive approach for input ["+StringUtils.join(input, ",")+"]");
      for(ArrayList<Integer> output: recursiveOutput){
         System.out.println(StringUtils.join(output, "<-"));
      }
      System.out.println("End printing solution vector for recursive approach for input ["+StringUtils.join(input, ",")+"]. Solution vector size : "+recursiveOutput.size());
      generateSubSetsIterativeUtil = new GenerateAllSubSetsIterative<Integer>(input);
      Iterator<ArrayList<Integer>> iterator = generateSubSetsIterativeUtil.getIterator();
      long subSetCount = 0;
      System.out.println("Printing solution vector for iterative approach for input ["+StringUtils.join(input, ",")+"]");
      while(iterator.hasNext()){
         subSetCount++;
         System.out.println(StringUtils.join(iterator.next(), "->"));
      }
      Assert.assertEquals(subSetCount, (long )Math.pow(2, input.length), "Subset count did not match for iterative solution");
      System.out.println("End printing solution vector for iterative approach for input ["+StringUtils.join(input, ",")+"]. Solution vector size : "+subSetCount);
   }

}

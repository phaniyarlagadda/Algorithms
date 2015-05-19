package com.rationalcoding.combinatorics.tests;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.rationalcoding.combinatorics.BuildExpression;

public class BuildExpressionTest {
   
   @Test
   public void testExpression(){
      char[] input = {'1','2', '3', '6'};
      testExprUtil(input, 18, 89);
   }
   
   @Test
   public void testExpressionSingleElement(){
      char[] input = {'1'};
      testExprUtil(input, 1, 1);
   }
   
   @Test
   public void testExpressionTwoElement(){
      char[] input = {'1','2'};
      testExprUtil(input, 3, 2);
   }
   
   private void testExprUtil(char[] input, int targetSum, int expectedExpressionCount){
      BuildExpression exp = new BuildExpression();
      List<String> output = exp.getpermutations(input, targetSum);
      
      System.out.println("Printing expressions for input ["+StringUtils.join(ArrayUtils.toObject(input), ",")+"]");
      for(String expr: output){
         System.out.println(expr);
      }
      Assert.assertEquals(output.size(), expectedExpressionCount);
      System.out.println("End printing expressions for input ["+StringUtils.join(ArrayUtils.toObject(input), ",")+"]");
   }

}

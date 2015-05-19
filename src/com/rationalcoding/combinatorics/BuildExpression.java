package com.rationalcoding.combinatorics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

public class BuildExpression {
   private List<String> results;
   private int expressionlength ;
   // opeartors that can be used
   private char[] operators = {'+','-','*','/'};
   
   public List<String> getpermutations(char[] input, int targetSum){
      results = new ArrayList<String>();
      expressionlength = input.length*2-1;
      char[] partialSoln = new char[expressionlength];
      Arrays.fill(partialSoln, ' ');
      backtrack(partialSoln, 0, input, targetSum);
      return results;
   }
   
   private void backtrack(char[] partialSoln, int k, char[] input, int targetSum)
   {
      int maxLength = input.length + operators.length;
      char[] remaining = new char[maxLength];
      if (isSolution(partialSoln, k, input)) {
         processSolution(partialSoln, k, input, targetSum);
         return;
      }

      int possibleCandidateCount = constructCandidates(partialSoln, k, input, remaining);
      for (int i = 0; i < possibleCandidateCount; i++) {
         partialSoln[k] = remaining[i];
         backtrack(partialSoln, k + 1, input, targetSum);
      }

   }
   
   /**
    * Util method to evaluate the reverse polish notation
    * Division by zero is handled as output as 0.
    * @param expression
    * @return
    */
   private float evaluateReverPolishNotation(char[] expression){
      Stack<Float> exprStack = new Stack<Float>();
      for(int i=0;i<expression.length;i++){
         if(Character.isDigit(expression[i])){
            exprStack.push((float)Character.getNumericValue(expression[i]));
         }else{
            Float b = exprStack.pop();
            Float a = exprStack.pop();
            Float result = 0.0f; 
            switch(expression[i]){
            case '+':
               result = a+b;
               break;
            case '-':
               result = a-b;
               break;
            case '/':
               if(b == 0){
                  // deal with division by 0
                  result = 0.0f;
               }else{
                  result = a/b;
               }
               break;
            case '*':
               result = a*b;
               break;
            }
            exprStack.push(result);
         }
      }
      return exprStack.pop();
   }
   
   private void processSolution(char[] partialSoln,int k,char[] input, int targetSum)
   {  
      float expResult = evaluateReverPolishNotation(partialSoln);
      if(((int )expResult) == targetSum){
         results.add(StringUtils.join(ArrayUtils.toObject(partialSoln)," "));
      }     
      
   }
   private boolean isSolution(char[] partialSoln,int k,char[] input)
   {
      return k==expressionlength;
   }
   private int constructCandidates(char[] fixed,int k,char[] input, char[] remaining)
   {
      boolean in_perm[] = new boolean[input.length];
      Arrays.fill(in_perm, false);
      int operatorCount=0;
      // check what elements are in expression already
      for (int i = 0; i < k; i++) {
         if (Character.isDigit(fixed[i])){
            in_perm[getIndex(input, fixed[i])] = true;
            operatorCount++;
         }else if(fixed[i] == ' '){
            
         }else{
            operatorCount--;
         }
      }
      int choices = 0;
      // it can be number or operator or number
      if (operatorCount > 1) {
         for (int j = 0; j < operators.length; j++) {
            remaining[choices] = operators[j];
            choices++;
         }
         if(k > 1){
            for (int i = 0; i < in_perm.length; i++) {
               if (!in_perm[i]) {
                  remaining[choices] = input[i];
                  choices++;
               }

            }
         }

      } else {
         for (int i = 0; i < in_perm.length; i++) {
            if (!in_perm[i]) {
               remaining[choices] = input[i];
               choices++;
            }

         }
      }

      return choices;
   }
   
   private int getIndex(char[] input, char target){
      for(int i=0;i<input.length;i++){
         if(input[i] == target){
            return i;
         }
      }
      return -1;
   }
}

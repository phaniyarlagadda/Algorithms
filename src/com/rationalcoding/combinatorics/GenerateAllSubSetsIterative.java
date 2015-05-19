package com.rationalcoding.combinatorics;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

/**
 * Iterative approach to generating all subsets of give input array.
 * Each solution in solution vector is generated based on its index. The index in binary number format is used to generate the output.
 * The indexes in binary format cover all subsets.
 * This approach is particularly useful when the output size is very large and we cannot hold all the output in memory at once.
 * Ref : The Algorithm Design by Steven Skienna
 * @author yarlagadda
 *
 * @param <T>
 */
public class GenerateAllSubSetsIterative<T> {
   
   private T[] input;

   public GenerateAllSubSetsIterative(T[] input) {
      this.input = input;
   }
   
   public Iterator<ArrayList<T>> getIterator(){
      return new GenerateAllSubSetsIterator();
   }

   private ArrayList<T> getNextSubSet(long solutionVectorIndex){
      ArrayList<T> currentSubSet = new ArrayList<T>();
      String binaryIndex = StringUtils.leftPad(Long.toBinaryString(solutionVectorIndex), input.length, "0");
      for(int index=0; index < binaryIndex.length(); index++){
         if(binaryIndex.charAt(index) == '1'){
            currentSubSet.add(input[index]);
         }
      }
      return currentSubSet;
   }   
   
   private class GenerateAllSubSetsIterator implements Iterator<ArrayList<T>> {

      private long currentElementIndex = 0;
      // size of the solution vector. Cast this to long.
      private long maxLength = (long) Math.pow(2, input.length);

      @Override
      public boolean hasNext() {
         return currentElementIndex < maxLength;
      }

      @Override
      public ArrayList<T> next() {
         return getNextSubSet(currentElementIndex++);
      }

      @Override
      public void remove() {
         throw new UnsupportedOperationException();
      }

   }

}

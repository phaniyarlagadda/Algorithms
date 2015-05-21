package com.rationalcoding.combinatorics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Generic class which generates combinations from list of lists
 * The combinations are generated iteratively
 * @author yarlagadda
 *
 * @param <T>
 */
public class CombinationsOfListsIterative<T> implements Iterable<ArrayList<T>>{
   
   List<ArrayList<T>> input;
   
   public CombinationsOfListsIterative(List<ArrayList<T>> input){
      this.input = input;
   }
   
   @Override
   public Iterator<ArrayList<T>> iterator() {
      
      return new CombinationsOfListsIterator();
   }
   
   private class CombinationsOfListsIterator implements Iterator<ArrayList<T>> {
      
      private long maxCombinations = 1;
      private long currentIndex = 0;
      private int[] currentIndexRegistry;
      private int[] maxIndexRegistry;
      
      public CombinationsOfListsIterator(){
         maxIndexRegistry = new int[input.size()];
         currentIndexRegistry = new int[input.size()];
         for(int index=0; index < input.size(); index++){
            maxCombinations *= input.get(index).size();
            maxIndexRegistry[index] = input.get(index).size();
         }
         Arrays.fill(currentIndexRegistry, 0);
         // set last bit differently so that first combination is accounted when we add
         currentIndexRegistry[input.size()-1] = -1;
      }
      
      @Override
      public boolean hasNext() {
         return (currentIndex < maxCombinations);
      }

      @Override
      public ArrayList<T> next() {
         // add 1 to LSB.
         for(int index=input.size()-1; true; index--){
            currentIndexRegistry[index] += 1;
            if(currentIndexRegistry[index] == maxIndexRegistry[index]){
               // reset and carry forward
               currentIndexRegistry[index] = 0;
            }else{
               // if no carry forward break
               break;
            }
         }
         // process the output based on the counter registry
         ArrayList<T> combination = new ArrayList<T>();
         for(int listIndex=0; listIndex < currentIndexRegistry.length; listIndex++){
            combination.add(input.get(listIndex).get(currentIndexRegistry[listIndex]));
         }
         currentIndex++;
         return combination;
      }

      @Override
      public void remove() {
         throw new UnsupportedOperationException();
         
      }
      
   }

}

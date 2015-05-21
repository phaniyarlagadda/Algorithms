package com.rationalcoding.combinatorics.tests;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.rationalcoding.combinatorics.CombinationsOfListsIterative;
import com.rationalcoding.combinatorics.CombinationsOfListsRecursive;

/**
 * Automated tests to verify the generation of combinations from list of lists
 * Output has to be verified manually. Only count of combination is asserted
 * @author yarlagadda
 *
 */
public class CombinationOfListsTest {
   
   @Test
   public void basicTest(){
      ArrayList<String> browsers = new ArrayList<String>();
      browsers.add("chrome");
      browsers.add("firefox");
      browsers.add("safari");
      ArrayList<String> os = new ArrayList<String>();
      os.add("chromeos");
      os.add("osx");
      ArrayList<String> display = new ArrayList<String>();
      display.add("mobile");
      display.add("desktop");
      ArrayList<ArrayList<String>> input = new ArrayList<ArrayList<String>>();
      input.add(browsers);
      input.add(os);
      input.add(display);
      printAndAssertOutput(input);
   }
   
   @Test
   public void emptyListTest(){
      ArrayList<String> browsers = new ArrayList<String>();
      browsers.add("chrome");
      browsers.add("firefox");
      browsers.add("safari");
      ArrayList<String> os = new ArrayList<String>();
      ArrayList<String> display = new ArrayList<String>();
      display.add("mobile");
      display.add("desktop");
      ArrayList<ArrayList<String>> input = new ArrayList<ArrayList<String>>();
      input.add(browsers);
      input.add(os);
      input.add(display);
      printAndAssertOutput(input);
   }
   
   @Test
   public void veryLargeTest(){
      // this test will fail for recursive util unless memory is increased
      ArrayList<String> aindex = new ArrayList<String>();
      ArrayList<String> bindex = new ArrayList<String>();
      // sample large list
      for(int i=0; i<30000; i++){
         aindex.add("a"+i);
         bindex.add("b"+i);
      }
      ArrayList<ArrayList<String>> input = new ArrayList<ArrayList<String>>();
      input.add(aindex);
      input.add(bindex);
      long expectedSize = 1;
      for(ArrayList<String> i:input){
         expectedSize *= i.size();
      }
      long actualCount = 0;
      // just assert the count. printing will blow up your screen :-)
      CombinationsOfListsIterative<String> iterativeUtil = new CombinationsOfListsIterative<String>(input);
      for(ArrayList<String> combination: iterativeUtil){
         actualCount++;
      }
      Assert.assertEquals(actualCount, expectedSize);
      
   }
   
   private void printAndAssertOutput(ArrayList<ArrayList<String>> input){
      System.out.println("Printing output");
      int expectedSize = 1;
      for(ArrayList<String> i:input){
         expectedSize *= i.size();
      }
      CombinationsOfListsRecursive<String> recursiveUtil = new CombinationsOfListsRecursive<String>();
      List<ArrayList<String>> output = recursiveUtil.getCombinationOfLists(input);
      for(ArrayList<String> combination:output){
         System.out.println(StringUtils.join(combination, "->"));
      }
      Assert.assertEquals(output.size(), expectedSize);
      CombinationsOfListsIterative<String> iterativeUtil = new CombinationsOfListsIterative<String>(input);
      for(ArrayList<String> combination: iterativeUtil){
         System.out.println(StringUtils.join(combination, "<-"));
      }
      System.out.println("End printing output");
   }

}

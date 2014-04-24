package com.rationalcoding.combinatorics;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Util class to generate combinations
 * 
 */
public class SubSetSum {

	/**
	 * public method which is exposed for calling. Input is a sorted array of
	 * positive numbers. Target sum is desired target sum.
	 * 
	 * @param input
	 * @param targetSum
	 * @return
	 */
	public ArrayList<ArrayList<Integer>> getCombinations(int[] input, int targetSum) {
		// list of lists to hold required combinations
		ArrayList<ArrayList<Integer>> combinations = new ArrayList<ArrayList<Integer>>();
		// using array deque since it provides better implementation of stack
		ArrayDeque<Integer> currentElementStack = new ArrayDeque<Integer>();
		// generate combinations is called with starting element and empty stack and
		// current stack sum as zero
		generateCombinations(input, targetSum, 0, currentElementStack, 0, combinations);
		return combinations;
	}

	/**
	 * Util method to generate combinations and add the found combination to
	 * required combination list. This method calls itself recursively. It starts
	 * from start index of input array until end. At each step of loop we see if
	 * the element fits the combination. If sum of element and sum of all elements
	 * in stack is equal to target then a combintion is found so element is pushed
	 * onto stack. If sum of element and sum of all elements in stack is less than
	 * target we will try recursively on the remaining elements if any of it fits
	 * the combination by pushing the current element onto stack If sum of element
	 * and sum of all elements in stack is greater than target we can break loop
	 * since remaining elements all are greater and wont fit the combination After
	 * recursion ends element is popped and loop continues with next element
	 * Recursion ends when end of array is reached or current sum has become
	 * greater than target sum. When a valid combination is found it is added to
	 * list of combinations
	 * 
	 * @param input
	 * @param targetSum
	 * @param startIndex
	 * @param currentElementsStack
	 * @param currentStackSum
	 * @param combinations
	 */
	private void generateCombinations(int[] input, int targetSum, int startIndex,
			ArrayDeque<Integer> currentElementsStack, int currentStackSum,
			ArrayList<ArrayList<Integer>> combinations) {

		// loop through elements from current start index till end
		for (int currentIndex = startIndex; currentIndex < input.length; currentIndex++) {
			// see if the element fits in a combination.
			// if current element when added to current sum is less than or equal to
			// target sum then there is a chance it can be part of target sum
			int currentSum = currentStackSum + input[currentIndex];
			if (currentSum == targetSum) {
				// handle target sum found
				currentElementsStack.push(input[currentIndex]);
				handleTargetSumFound(currentElementsStack, combinations);
				currentElementsStack.pop();
				// if target sum is found then remianing elements will fit in required
				// combination since it is a increasing sequence so we can break
				break;
			} else if (currentSum < targetSum) {
				// if current sum is less than target sum try with remaining elements
				// call recursively on remaining elements from next index. recursion
				// ends when we tried all elements or no valid elements found
				currentElementsStack.push(input[currentIndex]);
				generateCombinations(input, targetSum, currentIndex + 1, currentElementsStack, currentSum,
						combinations);
				// pop the element and try with remaining elements in array
				currentElementsStack.pop();
			} else {
				// if current sum is greater than target sum we can break since elements
				// are sorted and in increasing order.
				// all the remaining elements wont fit the required combination
				break;
			}
		}
	}

	/**
	 * Util method to handle when target sum found. Creates an array list from
	 * elements of stack and adds the array list to required combination list
	 * 
	 * @param elementStack
	 * @param combinations
	 */
	private void handleTargetSumFound(ArrayDeque<Integer> elementStack,
			ArrayList<ArrayList<Integer>> combinations) {
		// iterate through current stack elements and add them to required
		// combination
		ArrayList<Integer> foundCombination = new ArrayList<Integer>();
		Iterator<Integer> elementIterator = elementStack.iterator();
		while (elementIterator.hasNext()) {
			foundCombination.add(elementIterator.next());
		}
		// reverse the array list for better readability
		Collections.reverse(foundCombination);
		combinations.add(foundCombination);
	}
}

package com.rationalcoding.combinatorics;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GenerateCombinations {
	
	/**
	 * Generates combinations from input list with a combination size.
	 * @param list
	 * @param combinationSize
	 * @return
	 */
	public ArrayList<ArrayList<Integer>> generateCombinations(List<Integer> list, int combinationSize) {
		ArrayList<ArrayList<Integer>> combinations = new ArrayList<ArrayList<Integer>>();
		generateCombinationUtil(list, combinationSize, 0, new ArrayDeque<Integer>(), combinations);
		return combinations;
	}

	private void generateCombinationUtil(List<Integer> list, int combinationSize, int currentIndex,
			ArrayDeque<Integer> currentElementStack, ArrayList<ArrayList<Integer>> combinations) {
		for (int index = currentIndex; index < (list.size()); index++) {
			currentElementStack.push(list.get(index));
			if (currentElementStack.size() == combinationSize) {
				addCombinationFound(currentElementStack, combinations);
			} else {
				generateCombinationUtil(list, combinationSize, index + 1, currentElementStack, combinations);
			}

			currentElementStack.pop();
		}
	}

	private void addCombinationFound(ArrayDeque<Integer> currentElementStack,
			ArrayList<ArrayList<Integer>> combinations) {
		Iterator<Integer> elementIterator = currentElementStack.iterator();
		ArrayList<Integer> foundCombination = new ArrayList<Integer>();
		while (elementIterator.hasNext()) {
			foundCombination.add(elementIterator.next());
		}
		Collections.reverse(foundCombination);
		combinations.add(foundCombination);
	}
}

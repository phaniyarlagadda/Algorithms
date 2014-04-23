package com.rationalcoding.combinatorics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Generates permutations
 * 
 * @author yarlagadda
 * 
 */
public class GeneratePermutations {

	/**
	 * generates permutations of input string. can handle string with duplicate
	 * characters
	 * 
	 * @param input
	 * @return
	 */
	public Set<String> generatePermutations(String input) {
		Set<String> permutations = new HashSet<String>();
		// for each character generate possible permutations till that point
		for (int charIndex = 0; charIndex < input.length(); charIndex++) {
			permutations = generatePermutationsUtil(input.charAt(charIndex), permutations);
		}
		return permutations;
	}
	
	/**
	 * Util to generate permutations with the character and current permutations
	 * @param currentChar
	 * @param currentPermutations
	 * @return
	 */
	private Set<String> generatePermutationsUtil(char currentChar, Set<String> currentPermutations) {
		Set<String> newPermutations = new HashSet<String>();
		if (currentPermutations.size() == 0) {
			newPermutations.add(currentChar + "");
		}
		for (String permutation : currentPermutations) {
			for (int currentIndex = 0; currentIndex <= permutation.length(); currentIndex++) {
				StringBuffer temp = new StringBuffer(permutation);
				temp.insert(currentIndex, currentChar);
				newPermutations.add(temp.toString());
			}
		}
		return newPermutations;
	}

	/**
	 * generates permutations of input array. cannot handle array with duplicate
	 * elements
	 * 
	 * @param input
	 * @return
	 */
	public List<ArrayList<Integer>> generatePermutations(int[] input) {
		List<ArrayList<Integer>> permutationsList = new ArrayList<ArrayList<Integer>>();
		for (int el : input) {
			permutationsList = generatePermutationsUtil(el, permutationsList);
		}
		return permutationsList;
	}

	private List<ArrayList<Integer>> generatePermutationsUtil(int currentElement,
			List<ArrayList<Integer>> currentPermutations) {
		ArrayList<ArrayList<Integer>> permutationsList = new ArrayList<ArrayList<Integer>>();
		if (currentPermutations.size() == 0) {
			ArrayList<Integer> permutations = new ArrayList<Integer>();
			permutations.add(currentElement);
			permutationsList.add(permutations);
		}
		for (ArrayList<Integer> permute : currentPermutations) {
			for (int index = 0; index <= permute.size(); index++) {
				ArrayList<Integer> temp = new ArrayList<Integer>(permute);
				temp.add(index, currentElement);
				permutationsList.add(temp);
			}
		}
		return permutationsList;

	}

}

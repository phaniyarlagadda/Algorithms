package com.rationalcoding.combinatorics.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rationalcoding.combinatorics.GeneratePermutations;

public class GeneratePermutationsIntegerTest {
	private GeneratePermutations generatePermutations;

	@BeforeClass
	public void oneTimeSetUp() {
		generatePermutations = new GeneratePermutations();
	}

	@Test
	public void testSingleElement() {
		int[] input = { 2 };
		List<ArrayList<Integer>> permutations = generatePermutations.generatePermutations(input);
		assertPermutations(input, permutations);
	}
	
	@Test
	public void testEmptyArray() {
		int[] input = {};
		List<ArrayList<Integer>> permutations = generatePermutations.generatePermutations(input);
		assertPermutations(input, permutations);
	}
	
	@Test
	public void testEvenNumberSizeArray() {
		int[] input = { 2, 9 };
		List<ArrayList<Integer>> permutations = generatePermutations.generatePermutations(input);
		assertPermutations(input, permutations);
	}
	
	@Test
	public void testOddNumberSizeArray() {
		int[] input = { 2, 9, 12 };
		List<ArrayList<Integer>> permutations = generatePermutations.generatePermutations(input);
		assertPermutations(input, permutations);
	}
	
	@Test
	public void testLargeArray() {
		int[] input = { 1,2,3,4,5,6,7,8,9};
		List<ArrayList<Integer>> permutations = generatePermutations.generatePermutations(input);
		assertPermutations(input, permutations);
	}

	private void assertPermutations(int[] input, List<ArrayList<Integer>> permutations) {
		System.out.println("Printing permutations for " + Arrays.toString(input));
		Assert.assertEquals(permutations.size(), CombinationUtils.factorial(input.length),"Number of combinations generated does not match expected number");
		for (ArrayList<Integer> p : permutations) {
			System.out.println(StringUtils.join(p, ","));
			Assert.assertEquals(p.size(), input.length,
					"Permuted list and input array lengths dont match");
		}
		System.out.println("End printing combinations");
	}
}

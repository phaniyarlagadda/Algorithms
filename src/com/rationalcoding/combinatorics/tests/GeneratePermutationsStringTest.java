package com.rationalcoding.combinatorics.tests;

import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rationalcoding.combinatorics.GeneratePermutations;

public class GeneratePermutationsStringTest {
	private GeneratePermutations generatePermutations;

	@BeforeClass
	public void oneTimeSetUp() {
		generatePermutations = new GeneratePermutations();
	}

	@Test
	public void testSmipleString() {
		String input = "abc";
		Set<String> permutations = generatePermutations.generatePermutations(input);
		assertPermutations(input,permutations);
	}

	@Test
	public void testEmptyString() {
		String input = "";
		Set<String> permutations = generatePermutations.generatePermutations(input);
		assertPermutations(input,permutations);
	}
	
	private void assertPermutations(String input, Set<String> permutations) {
		System.out.println("Printing permutations for " + input);
		for (String p : permutations) {
			System.out.println(p);
			Assert.assertEquals(p.length(), input.length(),
					"Permuted string and input string lengths dont match");
		}
		System.out.println("End printing combinations");
	}
}

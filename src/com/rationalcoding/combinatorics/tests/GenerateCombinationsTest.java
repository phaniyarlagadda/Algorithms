package com.rationalcoding.combinatorics.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rationalcoding.combinatorics.GenerateCombinations;

public class GenerateCombinationsTest {
	GenerateCombinations combinationUtil = new GenerateCombinations();

	@DataProvider(name = "combinationSizeDP")
	public Object[][] combinationSizeProvider() {
		Object[][] combinationSizes = { { 1 }, { 2 }, { 3 }, { 4 } };
		return combinationSizes;
	}

	@Test(dataProvider = "combinationSizeDP")
	public void testSimpleCombination(int combinationSize) {
		List<Integer> uniqueInputArray = Arrays.asList(2, 6, 4, 9);
		ArrayList<ArrayList<Integer>> combinations = combinationUtil.generateCombinations(
				uniqueInputArray, combinationSize);
		assertCombinations(uniqueInputArray, combinationSize, combinations);
	}

	private void assertCombinations(List<Integer> input, int combinationSize,
			ArrayList<ArrayList<Integer>> combinations) {
		System.out.println("Printing combinations for input " + StringUtils.join(input, ",")
				+ " and combination size:" + combinationSize);
		int expectedCombinations = 0;
		if(input.size() == combinationSize){
			expectedCombinations = 1;
		}else if(input.size() > 0 ){
			expectedCombinations = CombinationUtils.factorial(input.size())
					/ (CombinationUtils.factorial(combinationSize) * CombinationUtils.factorial(input.size() - combinationSize));	
		}
		Assert.assertEquals(combinations.size(), expectedCombinations,
				"Number of combinations expected did not match");
		for (ArrayList<Integer> combintionFound : combinations) {
			System.out.println(StringUtils.join(combintionFound, ","));
			Assert.assertEquals(combintionFound.size(), combinationSize,
					"Individual combination size did not match");
		}
	}
}

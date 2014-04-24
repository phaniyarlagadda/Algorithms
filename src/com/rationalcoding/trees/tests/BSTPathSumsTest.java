package com.rationalcoding.trees.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rationalcoding.trees.BSTPathSums;
import com.rationalcoding.trees.TreeNode;

/**
 * This test cases assume tree cannot have Integer.MIN_VALUE as node
 * @author yarlagadda
 *
 */
public class BSTPathSumsTest {
	BSTPathSums bstUtil;

	@BeforeClass
	public void oneTimeSetUp() {
		bstUtil = new BSTPathSums();
	}

	@Test
	public void testTwoLevelTreePathFound() {
		List<Integer> twoLevelTree = Arrays.asList(4, 2, Integer.MIN_VALUE, Integer.MIN_VALUE, 10,
				Integer.MIN_VALUE, Integer.MIN_VALUE);
		TreeNode root = buildTree(twoLevelTree.iterator());
		printPretty(root, 0);
		int targetSum = 6;
		int expectedNumberOfPaths = 1;
		List<ArrayList<TreeNode>> paths = bstUtil.getPathsWithSum(root, targetSum);
		assertPathsFound(paths, targetSum, expectedNumberOfPaths);
	}

	@Test
	public void testTwoLevelTreePathNotFound() {
		List<Integer> twoLevelTree = Arrays.asList(4, 2, Integer.MIN_VALUE, Integer.MIN_VALUE, 10,
				Integer.MIN_VALUE, Integer.MIN_VALUE);
		TreeNode root = buildTree(twoLevelTree.iterator());
		printPretty(root, 0);
		int targetSum = 10;
		int expectedNumberOfPaths = 0;
		List<ArrayList<TreeNode>> paths = bstUtil.getPathsWithSum(root, targetSum);
		assertPathsFound(paths, targetSum, expectedNumberOfPaths);
	}

	@Test
	public void testThreeLevelTreePathFound() {
		List<Integer> twoLevelTree = Arrays.asList(10, 6, 4, Integer.MIN_VALUE, Integer.MIN_VALUE, 8,
				Integer.MIN_VALUE, Integer.MIN_VALUE, 16, 14, Integer.MIN_VALUE, Integer.MIN_VALUE, 24,
				Integer.MIN_VALUE, Integer.MIN_VALUE);
		TreeNode root = buildTree(twoLevelTree.iterator());
		printPretty(root, 0);
		int targetSum = 24;
		int expectedNumberOfPaths = 1;
		List<ArrayList<TreeNode>> paths = bstUtil.getPathsWithSum(root, targetSum);
		assertPathsFound(paths, targetSum, expectedNumberOfPaths);
	}

	@Test
	public void testMultiplePaths() {
		List<Integer> twoLevelTree = Arrays.asList(10, 6, 5, Integer.MIN_VALUE, Integer.MIN_VALUE, 8,
				Integer.MIN_VALUE, Integer.MIN_VALUE, 11, Integer.MIN_VALUE, Integer.MIN_VALUE);
		TreeNode root = buildTree(twoLevelTree.iterator());
		printPretty(root, 0);
		int targetSum = 21;
		int expectedNumberOfPaths = 2;
		List<ArrayList<TreeNode>> paths = bstUtil.getPathsWithSum(root, targetSum);
		assertPathsFound(paths, targetSum, expectedNumberOfPaths);
	}

	@Test
	public void testMultiplePathsMerged() {
		List<Integer> twoLevelTree = Arrays.asList(10, 6, 5, 3, Integer.MIN_VALUE, Integer.MIN_VALUE,
				Integer.MIN_VALUE, 8, Integer.MIN_VALUE, Integer.MIN_VALUE, 11, Integer.MIN_VALUE,
				Integer.MIN_VALUE);
		TreeNode root = buildTree(twoLevelTree.iterator());
		printPretty(root, 0);
		int targetSum = 24;
		int expectedNumberOfPaths = 2;
		List<ArrayList<TreeNode>> paths = bstUtil.getPathsWithSum(root, targetSum);
		assertPathsFound(paths, targetSum, expectedNumberOfPaths);
	}

	@Test
	public void testSingleElement() {
		List<Integer> twoLevelTree = Arrays.asList(10, Integer.MIN_VALUE, Integer.MIN_VALUE);
		TreeNode root = buildTree(twoLevelTree.iterator());
		printPretty(root, 0);
		int targetSum = 10;
		int expectedNumberOfPaths = 1;
		List<ArrayList<TreeNode>> paths = bstUtil.getPathsWithSum(root, targetSum);
		assertPathsFound(paths, targetSum, expectedNumberOfPaths);
	}

	@Test
	public void testSingleElementPathNotFound() {
		List<Integer> twoLevelTree = Arrays.asList(10, Integer.MIN_VALUE, Integer.MIN_VALUE);
		TreeNode root = buildTree(twoLevelTree.iterator());
		printPretty(root, 0);
		int targetSum = 4;
		int expectedNumberOfPaths = 0;
		List<ArrayList<TreeNode>> paths = bstUtil.getPathsWithSum(root, targetSum);
		assertPathsFound(paths, targetSum, expectedNumberOfPaths);
	}

	@Test
	public void testTreeWithNegativeNumbersAndTargetSum() {
		List<Integer> twoLevelTree = Arrays.asList(10, 6, -18, Integer.MIN_VALUE, Integer.MIN_VALUE, 8,
				Integer.MIN_VALUE, Integer.MIN_VALUE, 11, Integer.MIN_VALUE, Integer.MIN_VALUE);
		TreeNode root = buildTree(twoLevelTree.iterator());
		printPretty(root, 0);
		int targetSum = -2;
		int expectedNumberOfPaths = 1;
		List<ArrayList<TreeNode>> paths = bstUtil.getPathsWithSum(root, targetSum);
		assertPathsFound(paths, targetSum, expectedNumberOfPaths);
	}
	
	/**
	 * Util to assert paths found and print them
	 * @param paths
	 * @param targetSum
	 * @param expectedNumberOfPaths
	 */
	private void assertPathsFound(List<ArrayList<TreeNode>> paths, int targetSum,
			int expectedNumberOfPaths) {
		System.out.println("Printing paths for " + targetSum);
		Assert.assertEquals(paths.size(), expectedNumberOfPaths,
				"Number of paths found did not match expected number of paths");
		System.out.println("Number of paths found " + paths.size());
		for (ArrayList<TreeNode> path : paths) {
			int actualSum = 0;
			// reverse the path just to make it easy
			Collections.reverse(path);
			for (TreeNode node : path) {
				System.out.print(node.getKey() + "->");
				actualSum += node.getKey();
			}
			System.out.println();
			Assert.assertEquals(actualSum, targetSum, "Actual sum and target sum dont match.");
		}
		System.out.println("End printing paths");
	}
	
	/**
	 * Converts an preorder collection of integers where each leaf is represented as Integer.MIN_VALUE
	 * @param elementIterator
	 * @return
	 */
	private TreeNode buildTree(Iterator<Integer> elementIterator) {
		// if iterator does not have any tokens then we finished processing
		if (!elementIterator.hasNext()) {
			return null;
		}
		// read next available token
		int currentElement = elementIterator.next();
		// if token is delimiter then its signal that we reached leaf so return
		// null.
		if (currentElement == Integer.MIN_VALUE) {
			return null;
		}
		// if token is valid then create a node. child connections are not
		// established yet
		TreeNode currentNode = new TreeNode(currentElement);
		// build left child subtree
		TreeNode leftChild = buildTree(elementIterator);
		// establish connection with left child
		currentNode.setLeftChild(leftChild);
		// build right child subtree
		TreeNode rightChild = buildTree(elementIterator);
		// establish connection with right child
		currentNode.setRightChild(rightChild);
		// established connections also so return the current node
		return currentNode;
	}
	
	/**
	 * Util to print tree 
	 * @param root
	 * @param level
	 */
	private void printPretty(TreeNode root, int level) {
		if (root == null)
			return;
		printPretty(root.getRightChild(), level + 1);
		if (level != 0) {
			for (int i = 0; i < level - 1; i++)
				System.out.print("|\t");
			System.out.println("|-------(" + root.getKey() + ")");
		} else
			System.out.println(root.getKey());
		printPretty(root.getLeftChild(), level + 1);
	}
}

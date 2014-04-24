package com.rationalcoding.trees;

import java.util.ArrayList;
import java.util.List;

public class BSTPathSums {

	public List<ArrayList<TreeNode>> getPathsWithSum(TreeNode root, int targetSum) {
		// a util is used so that we can check for inputs and other conditions if
		// neccessary here
		return getPathsWithSumUtil(root, targetSum);
	}

	private List<ArrayList<TreeNode>> getPathsWithSumUtil(TreeNode root, int targetSum) {
		List<ArrayList<TreeNode>> mergedPaths = new ArrayList<ArrayList<TreeNode>>();
		// recursion ends when root is null
		if (root == null) {
			return mergedPaths;
		}
		// recursion ends when root is leaf
		if (root.getLeftChild() == null && root.getRightChild() == null) {
			// if leaf and target sum is root key then a path is found
			if (root.getKey() == targetSum) {
				ArrayList<TreeNode> path = new ArrayList<TreeNode>();
				path.add(root);
				mergedPaths.add(path);
			}
			return mergedPaths;
		}
		// if root is not leaf recursively search on left and right sub trees with new target sum as difference of target sum and root value
		List<ArrayList<TreeNode>> leftSubTreePaths = new ArrayList<ArrayList<TreeNode>>();
		List<ArrayList<TreeNode>> rightSubTreePaths = new ArrayList<ArrayList<TreeNode>>();
		if (root.getLeftChild() != null) {
			leftSubTreePaths = getPathsWithSumUtil(root.getLeftChild(), targetSum - root.getKey());
		}
		if (root.getRightChild() != null) {
			rightSubTreePaths = getPathsWithSumUtil(root.getRightChild(), targetSum - root.getKey());
		}
		
		// if paths are found on sub trees then add add root to each path
		for (ArrayList<TreeNode> path : leftSubTreePaths) {
			path.add(root);
		}
		for (ArrayList<TreeNode> path : rightSubTreePaths) {
			path.add(root);
		}
		// merge paths of left and right sub trees
		mergedPaths.addAll(leftSubTreePaths);
		mergedPaths.addAll(rightSubTreePaths);
		return mergedPaths;
	}

}

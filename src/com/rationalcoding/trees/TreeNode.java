package com.rationalcoding.trees;

public class TreeNode {
	
	private int key;
	private TreeNode leftChild;
	private TreeNode rightChild;
	public TreeNode(int key) {
		this.key = key;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public TreeNode getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(TreeNode leftChild) {
		this.leftChild = leftChild;
	}
	public TreeNode getRightChild() {
		return rightChild;
	}
	public void setRightChild(TreeNode rightChild) {
		this.rightChild = rightChild;
	}
	

}

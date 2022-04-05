package com.smxknife.datastructure.tree._01_binary_tree;

import java.util.LinkedList;

/**
 * 二叉树
 * @author smxknife
 * 2021/6/17
 */
public class TreeNode {
	public int data;
	public TreeNode leftChild;
	public TreeNode rightChild;

	public TreeNode(int data){
		this.data = data;
	}

	public static TreeNode createBinaryTree(LinkedList<Integer> list){
		TreeNode node = null;
		if(list == null || list.isEmpty()){
			return null;
		}
		Integer data = list.removeFirst();
		if(data!=null){
			node = new TreeNode(data);
			node.leftChild = createBinaryTree(list);
			node.rightChild = createBinaryTree(list);
		}
		return node;
	}
}

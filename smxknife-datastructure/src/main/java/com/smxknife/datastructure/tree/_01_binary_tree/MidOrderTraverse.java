package com.smxknife.datastructure.tree._01_binary_tree;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 二叉树中序遍历: 左 -》根 -》右
 * @author smxknife
 * 2021/6/17
 */
public class MidOrderTraverse {
	public static void main(String[] args) {
		final TreeNode treeNode = TreeNode.createBinaryTree(new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)));
		order(treeNode);
	}

	private static void order(TreeNode treeNode) {
		if (treeNode == null) {
			return;
		}
		order(treeNode.leftChild);
		System.out.println(treeNode.data);
		order(treeNode.rightChild);
	}
}

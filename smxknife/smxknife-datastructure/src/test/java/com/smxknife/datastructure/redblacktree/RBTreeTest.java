package com.smxknife.datastructure.redblacktree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author smxknife
 * 2019/11/21
 */
public class RBTreeTest {

	private RBTree tree;
	private NodeTraversalStrategy strategy;

	private RBNode target;

	@BeforeEach
	public void init() {
		// root
		RBNode root = new RBNode(10);
		// level 1
		RBNode left1 = new RBNode(8);target = left1;
		RBNode right1 = new RBNode(11);
		root.setLeft(left1);
		root.setRight(right1);
		// level2
		RBNode left12 = new RBNode(1);
		RBNode right12 = new RBNode(9);
		left1.setLeft(left12);
		left1.setRight(right12);

		strategy = new DeepNodeTraversalStrategy();
		tree = new RBTree(root, strategy);

	}

	@Test
	public void queryTest() {
		RBNode query = tree.query(8);
		System.out.println(query);
		assert target == query;
	}

}

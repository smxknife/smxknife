package com.smxknife.datastructure.redblacktree;

import lombok.Getter;
import lombok.Setter;

/**
 * @author smxknife
 * 2019/11/21
 */
public class RBTree {

	@Getter
	private RBNode root;
	@Getter@Setter
	private NodeTraversalStrategy strategy;

	public RBTree(RBNode root, NodeTraversalStrategy strategy) {
		this.root = root;
		this.strategy = strategy;
	}

	public RBNode query(int key) {
		return strategy.traversal(root, key);
	}

	public RBNode insert(int key) {
		return strategy.insert(root, key);
	}



}

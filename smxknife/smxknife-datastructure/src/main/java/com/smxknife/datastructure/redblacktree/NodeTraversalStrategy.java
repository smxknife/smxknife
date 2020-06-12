package com.smxknife.datastructure.redblacktree;

/**
 * @author smxknife
 * 2019/11/21
 */
public interface NodeTraversalStrategy {

	RBNode traversal(RBNode node, int key);

	RBNode insert(RBNode root, int key);
}

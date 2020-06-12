package com.smxknife.datastructure.redblacktree;

import java.util.Objects;

/**
 * @author smxknife
 * 2019/11/21
 */
public class DeepNodeTraversalStrategy implements NodeTraversalStrategy {

	@Override
	public RBNode traversal(RBNode node, int key) {
		if (Objects.isNull(node)) {
			return null;
		}

		if (key == node.getKey()) {
			return node;
		}

		RBNode left = node.getLeft(), right = node.getRight();
		if (Objects.isNull(left) && Objects.isNull(right)) {
			return null;
		}

		RBNode res = traversal(left, key);
		if (Objects.isNull(res)) {
			res = traversal(right, key);
		}

		return res;
	}

	@Override
	public RBNode insert(RBNode root, int key) {
		if (key == root.getKey()) {
			return root;
		}

		RBNode node = new RBNode(key);
		RBNode temp = root;
		while (Objects.isNull(node.parent)) {
			if (Objects.isNull(temp)) {
				break;
			}
			if (key > temp.getKey()) {
				temp = temp.getRight();
			} else {
				temp = temp.getLeft();
			}


		}
		return null;
	}
}

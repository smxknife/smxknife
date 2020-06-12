package com.smxknife.datastructure.redblacktree;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @author smxknife
 * 2019/11/21
 */
@Getter
class RBNode {
	RBNode parent;
	RBNode left;
	RBNode right;
	@Setter
	char color;
	int key;

	public RBNode(int key) {
		this.key = key;
	}

	public RBNode(int key, RBNode left, RBNode right) {
		this.key = key;
		this.left = left;
		this.right = right;
	}

	public void setLeft(RBNode left) {
		this.left = left;
		left.parent = this;
	}

	public void setRight(RBNode right) {
		this.right = right;
		right.parent = this;
	}

	@Override
	public String toString() {
		return new StringBuilder("RBNode {parent: key=").append(key)
				.append(", left: key=").append(Objects.isNull(left) ? "null" : left.getKey())
				.append(", right: key=").append(Objects.isNull(right) ? "null" : right.getKey())
				.append("}").toString();
	}
}

package com.smxknife.datastructure.stack;

/**
 * @author smxknife
 * 2019-05-09
 */
public final class FixedCapacityArrayStack<T> extends AbsArrayStack<T> {

	private FixedCapacityArrayStack() {
		super();
	}

	public FixedCapacityArrayStack(int size) {
		super(size);
	}

	@Override
	protected boolean expandable() {
		return false;
	}
}

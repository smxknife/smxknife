package com.smxknife.java2.bloom;

import java.util.Objects;

/**
 * @author smxknife
 * 2021/4/13
 */
@FunctionalInterface
public interface BloomHash {
	/**
	 * hash
	 * @param object
	 * @return
	 */
	int hash(Object object);

	class DefaultBloomHash implements BloomHash {

		@Override
		public int hash(Object object) {
			return Objects.hash(object);
		}
	}
}

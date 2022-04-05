package com.smxknife.algorithm.demo01.breadthfirstsearch;

import java.util.List;

/**
 * @author smxknife
 * 2019-04-30
 */
public interface Graph<T extends Graph> {

	/**
	 * generations nodes
	 * @return
	 */
	List<T> generations();

	boolean isChecked();

	void setChecked(boolean check);
}

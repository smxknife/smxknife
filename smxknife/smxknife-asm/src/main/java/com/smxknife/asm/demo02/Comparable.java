package com.smxknife.asm.demo02;

/**
 * @author smxknife
 * 2018-12-18
 */
public interface Comparable extends Mesurable{
	int less = -1;
	int equal = 0;
	int gather = 1;
	int compareTo(Object o);
}

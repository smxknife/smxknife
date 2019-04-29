package com.smxknife.java2.generics;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author smxknife
 * 2019-04-17
 */
public class ExtendGeneric {

	// 编译报错
//	public <T extends Integer & String> void get(T t) {
//
//	}

	public <T extends Comparable & Serializable> void set(T t) {

	}

	public <T extends Integer & Collection & List> void put(T t) {

	}
}

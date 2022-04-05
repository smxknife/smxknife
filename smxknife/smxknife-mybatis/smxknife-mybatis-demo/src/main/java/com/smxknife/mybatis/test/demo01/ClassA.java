package com.smxknife.mybatis.test.demo01;

import java.util.Map;

/**
 * @author smxknife
 * 2020/11/22
 */
public class ClassA<K, V> {
	protected Map<K, V> map;
	protected Integer integer;

	public Map<K, V> getMap() {
		return map;
	}

	public void setMap(Map<K, V> map) {
		this.map = map;
	}
}

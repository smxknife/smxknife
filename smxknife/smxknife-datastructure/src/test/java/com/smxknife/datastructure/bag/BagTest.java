package com.smxknife.datastructure.bag;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

/**
 * @author smxknife
 * 2019-05-10
 */
public class BagTest {

	@Test
	public void test() {
		Bag<String> bag = new Bag<>();
		bag.add("aaa");
		bag.add("bbb");

		Iterator<String> iterator = bag.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}

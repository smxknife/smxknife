package com.smxknife.java2.nio.selector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * @author smxknife
 * 2020/10/14
 */
public class _06_Selector_Open {

	@Test
	public void selectorTest() {
		try(Selector selector = Selector.open();) {
			System.out.println(selector);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

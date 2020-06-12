package com.smxknife.java3._01_runtimecreateobject;

import com.smxknife.java2.unsafe.UnsafeWrapper;

/**
 * @author smxknife
 * 2019/12/24
 */
public class _06_Unsafe_Demo {
	public static void main(String[] args) {
		try {
			Object o = UnsafeWrapper.unsafe().allocateInstance(_0_CommObj.class);
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}
}

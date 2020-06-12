package com.smxknife.java3._01_runtimecreateobject;

import java.io.Serializable;

/**
 * @author smxknife
 * 2019/12/24
 */
public class _0_CommObj implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	static {
		System.out.println("CommObj static block");
	}

	public _0_CommObj() {
		System.out.println("CommObj constructor");
	}
}

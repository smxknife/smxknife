package com.smxknife.java3._01_runtimecreateobject;

/**
 * @author smxknife
 * 2019/12/24
 */
public class _02_Class_newInstance_Demo {

	public static void main(String[] args) {
		try {
			_0_CommObj commObj = _0_CommObj.class.newInstance();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}
}

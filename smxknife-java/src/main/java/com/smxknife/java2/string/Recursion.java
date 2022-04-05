package com.smxknife.java2.string;

/**
 * @author smxknife
 * 2018/11/12
 */
public class Recursion {

	@Override
	public String toString() {
		// 这里使用this是非常危险的，非常容易发生递归调用的情况，
		// this是就是这个对象调用this后又再次调用this，不断的递归调用，最后会抛出java.lang.StackOverflowError
		// 所以，如果想要打印当前对象的指针，可以调用super.toString()
		return "Recursion : " + this;
		// return "Recursion : " + super.toString();
	}
}

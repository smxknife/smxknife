package com.smxknife.java2.reflection.demo01;

/**
 * @author smxknife
 * 2020/11/22
 */
public class _CommonObj<T> {
	private String myFd1;
	private int mFd2;
	private boolean mFd3;

	private T t;

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public String getMyFd4() {
		return "fd4";
	}

	public void setMyFd4(String myFd4) {

	}

	public String getMyFd1() {
		return myFd1;
	}

	public void setMyFd1(String myFd1) {
		this.myFd1 = myFd1;
	}

	public int getmFd2() {
		return mFd2;
	}

	public void setmFd2(int mFd2) {
		this.mFd2 = mFd2;
	}

	public boolean ismFd3() {
		return mFd3;
	}

	public void setmFd3(boolean mFd3) {
		this.mFd3 = mFd3;
	}
}

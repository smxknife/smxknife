package com.smxknife.java2.lock.custom;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author smxknife
 * 2020/4/11
 */
public interface Lock {

	void lock() throws InterruptedException;

	void lock(long mills) throws InterruptedException, TimeoutException;

	void unlock();

	List<Thread> getBlockedThreads();
}

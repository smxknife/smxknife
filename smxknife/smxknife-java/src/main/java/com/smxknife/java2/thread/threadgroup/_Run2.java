package com.smxknife.java2.thread.threadgroup;

/**
 * @author smxknife
 * 2019/9/6
 */
public class _Run2 {
	public static void main(String[] args) {
		// 这个是为了验证网络上某篇文章的正确性
		ThreadGroup tg = Thread.currentThread().getThreadGroup();
		System.out.println("1. tg = " + tg);
		int n = 1;
		for (ThreadGroup tgn = tg; tgn != null; tg = tgn, tgn = tg.getParent()) {
			System.out.println(++n + ". tg = " + tg + " | tgn = " + tgn);
		}
	}
}

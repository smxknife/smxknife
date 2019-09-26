package com.smxknife.java2.lock.producer_consumer;

/**
 * @author smxknife
 * 2019/9/13
 */
public class Consumer extends RatioRunnable {

	public Consumer(String name, Repository repository) {
		super(name, repository);
	}

	@Override
	protected void action() {
		Object consume = this.getRepository().consume();
		System.out.println("[ Consumer ]" + this.getName() + " consume " + consume);
	}
}

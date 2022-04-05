package com.smxknife.java2.lock.producer_consumer;

/**
 * @author smxknife
 * 2019/9/13
 */
public interface Repository<E> {

	/**
	 * 生产
	 * @param e
	 */
	void produce(E e);

	/**
	 * 消费
	 * @return
	 */
	E consume();
}

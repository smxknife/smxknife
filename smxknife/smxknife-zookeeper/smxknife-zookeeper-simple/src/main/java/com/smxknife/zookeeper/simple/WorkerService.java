package com.smxknife.zookeeper.simple;

/**
 * @author smxknife
 * 2019/12/2
 */
public interface WorkerService {
	void setStatus(Worker worker, String status);
}

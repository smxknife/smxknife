package com.smxknife.zookeeper.simple;

/**
 * @author smxknife
 * 2019/12/1
 */
public interface MasterService {
	/**
	 * master运行方式：同步或异步
	 * @param master
	 * @throws InterruptedException
	 */
	void runForMaster(Master master) throws InterruptedException;

	/**
	 * 检查是否是活跃的master
	 * @param master
	 * @return
	 * @throws InterruptedException
	 */
	boolean checkMaster(Master master) throws InterruptedException;

	/**
	 * 设置元数据
	 * @param master
	 */
	void bootstrap(Master master);
}

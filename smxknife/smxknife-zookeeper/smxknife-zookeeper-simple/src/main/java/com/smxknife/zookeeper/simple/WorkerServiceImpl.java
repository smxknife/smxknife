package com.smxknife.zookeeper.simple;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

/**
 * @author smxknife
 * 2019/12/2
 */
public class WorkerServiceImpl implements WorkerService {

	@Override
	public void setStatus(Worker worker, String status) {
		worker.setStatus(status);
		updateStatus(worker, status);
	}

	// 这里加上synchronized是为了保证Zookeeper的更新顺序，因为在更新的时候会经常遇到CONNECTIONLOSS的状态，当遇到
	// 这种状态时需要重试，而重试可能导致多线程的顺序出现问题，所以，这里加上同步锁，在锁方法内遇到该错误就重试
	private synchronized void updateStatus(Worker worker, String status) {
		// 注意这里是==，不是equals
		if (status == worker.getStatus()) {
			// 版本号-1表示禁止版本号检查
			worker.getZk().setData(worker.getPath(), status.getBytes(), -1, new AsyncCallback.StatCallback() {
				@Override
				public void processResult(int rc, String path, Object ctx, Stat stat) {
					switch (KeeperException.Code.get(rc)) {
						case CONNECTIONLOSS:
							updateStatus(worker, (String) ctx);
							return;
					}
				}
			}, status);
		}
	}
}

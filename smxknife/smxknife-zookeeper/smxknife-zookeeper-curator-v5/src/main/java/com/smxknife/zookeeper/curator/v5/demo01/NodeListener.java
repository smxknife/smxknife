package com.smxknife.zookeeper.curator.v5.demo01;

import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;

/**
 * @author smxknife
 * 2020/9/22
 */
public class NodeListener implements CuratorCacheListener {
	@Override
	public void event(Type type, ChildData childData, ChildData childData1) {
		System.out.println("EventType = " + type + " | childData = " + childData + " | childData1 = " + childData1);
	}

	@Override
	public void initialized() {
		System.out.println("init....");
	}
}

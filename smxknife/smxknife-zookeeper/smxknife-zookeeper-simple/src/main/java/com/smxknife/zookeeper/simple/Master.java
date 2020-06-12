package com.smxknife.zookeeper.simple;

import lombok.Getter;
import lombok.Setter;
import org.apache.zookeeper.WatchedEvent;

/**
 * @author smxknife
 * 2019/11/30
 */
public class Master extends Znode {

	@Getter@Setter
	private boolean leader;
	private MasterService masterService;


	public Master(String hostPort, String path, MasterService masterService) {
		super(hostPort);
		this.path = path;
		this.masterService = masterService;
	}

	void runForMaster() throws InterruptedException{
		masterService.runForMaster(this);
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println("master [" + this.getServerId() + "] " + event);
	}
}

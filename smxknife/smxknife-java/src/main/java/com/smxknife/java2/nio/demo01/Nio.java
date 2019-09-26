package com.smxknife.java2.nio.demo01;

import java.net.SocketAddress;
import java.nio.channels.Selector;

/**
 * @author smxknife
 * 2019/9/10
 */
public interface Nio {

	/**
	 * nio name
	 * @return
	 */
	default String getName() {
		return "";
	}

	/**
	 * client or server weather is stopped
	 * @return
	 */
	boolean isStop();

	/**
	 * client or server 's Selector
	 * @return
	 */
	Selector getSelector();

	/**
	 * Selector select timeout
	 * @return
	 */
	long getSelectTimeout();

	/**
	 * get the SelectionKey handler
	 * @return
	 */
	SelectionKeyHandler getSelectionKeyHandler();

	/**
	 * get socketAddress
	 * @return
	 */
	SocketAddress getSocketAddress();

}

package com.smxknife.game.hero;

import com.smxknife.game.hero.user.UserManager;

/**
 * @author smxknife
 * 2021/5/7
 */
public class ServerContext {
	private Broadcaster broadcaster;
	private UserManager userManager;

	public ServerContext() {
		this(Broadcaster.INSTANCE, UserManager.Default.INSTANCE);
	}

	public ServerContext(Broadcaster broadcaster, UserManager userManager) {
		this.broadcaster = broadcaster;
		this.userManager = userManager;
	}

	public Broadcaster getBroadcaster() {
		return broadcaster;
	}

	public UserManager getUserManager() {
		return userManager;
	}
}

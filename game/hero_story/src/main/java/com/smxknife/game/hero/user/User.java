package com.smxknife.game.hero.user;

import lombok.AllArgsConstructor;

/**
 * @author smxknife
 * 2021/5/6
 */
@AllArgsConstructor
public class User {
	private int userId;
	private String heroAvatar;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getHeroAvatar() {
		return heroAvatar;
	}

	public void setHeroAvatar(String heroAvatar) {
		this.heroAvatar = heroAvatar;
	}
}

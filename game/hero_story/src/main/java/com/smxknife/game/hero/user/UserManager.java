package com.smxknife.game.hero.user;

import lombok.NonNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author smxknife
 * 2021/5/7
 */
public interface UserManager {
	/**
	 * 添加user
	 * @param user
	 */
	void add(User user);

	/**
	 * 移除user
	 * @param userId
	 * @return
	 */
	User remove(int userId);

	/**
	 * 所有用户
	 * @return
	 */
	User[] listAll();

	enum Default implements UserManager {
		INSTANCE;

		private Map<Integer, User> userMap = new ConcurrentHashMap<>();

		@Override
		public void add(@NonNull User user) {
			userMap.putIfAbsent(user.getUserId(), user);
		}

		@Override
		public User remove(int userId) {
			return userMap.remove(userId);
		}

		@Override
		public User[] listAll() {
			return this.userMap.values().toArray(new User[this.userMap.size()]);
		}
	}
}

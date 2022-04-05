package com.smxknife.springboot._06_jsonview;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2021/8/1
 */
@RestController
@RequestMapping("/06/user")
public class UserController {

	@JsonView(User.UserDetail.class)
	@RequestMapping("detail")
	public User getUserDetail() {
		return new User("1111", "2222");
	}

	@JsonView(User.UserInfo.class)
	@RequestMapping("info")
	public User getUserInfo() {
		return new User("1111", "2222");
	}
}

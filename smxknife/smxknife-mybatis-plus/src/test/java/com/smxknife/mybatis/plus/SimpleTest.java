package com.smxknife.mybatis.plus;

import com.smxknife.mybatis.plus.entity.User;
import com.smxknife.mybatis.plus.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author smxknife
 * 2021/1/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTest {

	@Autowired
	UserMapper userMapper;

	@Test
	public void test() {
		System.out.println(("----- selectAll method test ------"));
		List<User> userList = userMapper.selectList(null);
		Assert.assertEquals(5, userList.size());
		userList.forEach(System.out::println);
	}
}

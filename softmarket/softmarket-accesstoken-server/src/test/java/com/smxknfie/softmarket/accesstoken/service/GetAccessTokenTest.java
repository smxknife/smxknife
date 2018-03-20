package com.smxknfie.softmarket.accesstoken.service;

import com.smxknfie.softmarket.accesstoken.AccessTokenBoot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AccessTokenBoot.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class GetAccessTokenTest {

	private String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx2a14718eae9bbd71&secret=8fcf52f2aa42db364cc6503a87659505";

	@Autowired
	RefreshAccessToken refreshAccessToken;

	@Test
	public void getAccessTokenTest() {
		try {
			refreshAccessToken.refresh();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

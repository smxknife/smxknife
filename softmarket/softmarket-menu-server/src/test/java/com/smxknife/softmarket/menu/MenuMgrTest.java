package com.smxknife.softmarket.menu;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)

@SpringBootTest(classes = MenuBoot.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class MenuMgrTest {

	@Value("${wechat.accessToken.url}")
	private String accessTokenUrl;

	@Test
	public void testCreate() {
		RestTemplate restTemplate = new RestTemplate();
//		String accessToken = restTemplate.getForObject(accessTokenUrl, String.class);

		String res = restTemplate.getForObject("http://localhost:10001/wechat/mgr/menu/create", String.class);
	}
}

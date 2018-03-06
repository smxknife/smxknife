package com.smxknfie.springboot.v1.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smxknife.springboot.v1.config.AppBoot;
import com.smxknife.springboot.v1.config.domain.User;
import javafx.scene.paint.Color;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppBoot.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class UserJsonComponentTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testSerialization() throws JsonProcessingException {
		User user = new User(Color.ALICEBLUE);
		String json = objectMapper.writeValueAsString(user);
		assertEquals("{\"color\":\"#f0f8ff\"}", json);
	}

	@Test
	public void testDeserialize() throws IOException {
		String json = "{\"color\":\"#f0f8ff\"}";
		User user = objectMapper.readValue(json, User.class);

		assertEquals(Color.ALICEBLUE, user.getColor());
	}
}

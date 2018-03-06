package com.smxknife.springboot.v2.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smxknife.springboot.v2.config.domain.User;
import javafx.scene.paint.Color;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@JsonTest
@RunWith(SpringRunner.class)
public class UserJsonComponentTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testSerialization() throws JsonProcessingException {
		User user = new User(javafx.scene.paint.Color.ALICEBLUE);
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

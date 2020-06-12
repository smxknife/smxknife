package com.smxknife.cloudnative.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

import java.util.stream.Stream;

/**
 * @author smxknife
 * 2020/5/22
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class DemoApplicationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private CatRepository catRepository;

	@Before
	public void before() {
		Stream.of("Felix", "Garfield", "Whiskers").forEach(name -> catRepository.save(new Cat(name)));
	}

	@Test
	public void catReflectedRead() throws Exception {
		MediaType mediaType = MediaType.parseMediaType("application/hal+json;charset=UTF-8");
		this.mvc.perform(MockMvcRequestBuilders.get("/cats"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(mediaType))
				.andExpect(mvcResult -> {
					String contentAsString = mvcResult.getResponse().getContentAsString();
					Assert.assertTrue(contentAsString.split("totalElements")[1].split(":")[1].trim().split(",")[0].equals("3"));
				});

	}
}

package com.smxknife.springboot.ebean.domain;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.Query;
import org.junit.Test;

/**
 * @author smxknife
 * 2018/8/30
 */
public class ConsumerTest {

	@Test
	public void insertFindDelete() {
		Consumer consumer = new Consumer();
		consumer.setName("Hello world");

		EbeanServer server = Ebean.getDefaultServer();

		// insert the customer in the DB
		server.save(consumer);

		Query<Consumer> consumerQuery = Ebean.find(Consumer.class);

		// Find by Id
		Consumer foundHello = server.find(Consumer.class, 1);

		System.out.print("hello " + foundHello.getName());

		// delete the customer
//		server.delete(consumer);
	}
}

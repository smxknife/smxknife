package com.smxknife.websocket.springboot.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

/**
 * @author smxknife
 * 2019/8/27
 */
@SpringBootApplication
@RestController
public class Demo1App {

	@Autowired
	WebSocketHandlerDemo1 handlerDemo1;

	public static void main(String[] args) {
		SpringApplication.run(Demo1App.class, args);
	}

	@GetMapping("/notice")
	public String notice(String count) {
		handlerDemo1.sendMsg(count, "currentTime: " + LocalTime.now());
		return "已发送";
	}
}

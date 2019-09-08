package com.smxknife.websocket.springboot.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/27
 */
@SpringBootApplication
@RestController
public class Demo1App {

	@Autowired
	WebSocketHandlerDemo1 handlerDemo1;

	ExecutorService executorService = Executors.newSingleThreadExecutor();

	public static void main(String[] args) {
		SpringApplication.run(Demo1App.class, args);
	}

	@GetMapping("/notice")
	public String notice(String count) {
		handlerDemo1.sendMsg(count, "currentTime: " + LocalTime.now());
		return "已发送";
	}

	@PostConstruct
	public void init() {
		executorService.execute(() -> {
			while (true) {
				System.out.println("wwwwww");
				handlerDemo1.sendMsg("1", "[send thread name] " + Thread.currentThread().getName());

				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					System.out.println("error " + Thread.currentThread().getName());
					e.printStackTrace();
				}
			}
		});

	}
}

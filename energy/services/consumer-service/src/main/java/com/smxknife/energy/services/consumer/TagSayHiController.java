package com.smxknife.energy.services.consumer;

import com.smxknife.energy.services.spi.TagSayHiService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2021/5/24
 */
@RestController
@RequestMapping("/say")
public class TagSayHiController {

	@DubboReference(version = "1.0", tag = "peer1")
	TagSayHiService sayHiService;

	@GetMapping("tag")
	public String say() {
		return sayHiService.sayHi();
	}

}

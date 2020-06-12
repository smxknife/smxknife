package com.smxknife.servlet.springboot.demo05;

import org.springframework.web.bind.annotation.*;

/**
 * @author smxknife
 * 2020/2/15
 */
@RestController
public class UploadConfigController {

	@RequestMapping(value = "/uploadConfigData", method = {RequestMethod.POST})
	public @ResponseBody String uploadConfigData(@RequestBody ConfigData data) {
		return null;
	}
}

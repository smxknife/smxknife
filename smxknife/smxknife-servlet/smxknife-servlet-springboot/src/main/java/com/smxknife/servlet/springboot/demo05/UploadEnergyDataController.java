package com.smxknife.servlet.springboot.demo05;

import org.springframework.web.bind.annotation.*;

/**
 * @author smxknife
 * 2020/2/15
 */
@RestController
public class UploadEnergyDataController {

	@RequestMapping(value = "/uploadEnergyData")
	public @ResponseBody String uploadEnergyData(@RequestParam(required = false) Long energyData) {
		return "sssss = " + energyData;
	}
}